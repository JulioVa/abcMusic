#!/usr/bin/env python
from __future__ import print_function

import argparse
import sys
import librosa
import os
import json

from pymongo import MongoClient


def beat_track(input_file, output_csv):
    print('Loading ', input_file)
    y, sr = librosa.load(input_file, sr=22050)

    hop_length = 512

    print('Tracking beats')
    tempo, beats = librosa.beat.beat_track(y=y, sr=sr, hop_length=hop_length)

    print('Estimated tempo: {:0.2f} beats per minute'.format(tempo))

    beat_times = librosa.frames_to_time(beats, sr=sr, hop_length=hop_length)

    print('Saving output to ', output_csv)
    librosa.output.times_csv(output_csv, beat_times)
    print('done!')


def process_arguments(args):
    parser = argparse.ArgumentParser(description='Beat tracking example')

    parser.add_argument('input_file',
                        action='store',
                        help='path to the input file (wav, mp3, etc)')

    parser.add_argument('output_file',
                        action='store',
                        help='path to the output file (csv of beat times)')

    return vars(parser.parse_args(args))


if __name__ == '__main__':
    client = MongoClient()
    db = client.music_database
    collection = db.songs_parameters

    for filename in os.listdir('../../../../../../run/media/luke/BADASS/BO'):
        y, sr = librosa.load('../../../../../../run/media/luke/BADASS/BO/' + filename, sr=22050)
        hop_length = 512
        print('Tracking beats')
        tempo, beats = librosa.beat.beat_track(y=y, sr=sr, hop_length=hop_length)
        print('Estimated tempo: {:0.2f} beats per minute'.format(tempo))
        beat_times = librosa.frames_to_time(beats, sr=sr, hop_length=hop_length)
        # onsets below:
        onsets = librosa.onset.onset_detect(y=y,
                                            sr=sr,
                                            hop_length=hop_length)

        print("Found {:d} onsets.".format(onsets.shape[0]))
        onset_times = librosa.frames_to_time(onsets,
                                             sr=sr,
                                             hop_length=hop_length)

        song = {"name": filename, "beats": json.dumps(beat_times.tolist()), "onset": json.dumps(onset_times.tolist())}
        collection.insert_one(song)
