dataset_path = "Dataset/"

import cv2
import numpy as np

class Dataset:

    def __init__(self):
        arr = []
        arr.append(cv2.imread(dataset_path + "0.png", cv2.IMREAD_GRAYSCALE))
        arr.append(cv2.imread(dataset_path + "1.png", cv2.IMREAD_GRAYSCALE))
        arr.append(cv2.imread(dataset_path + "2.png", cv2.IMREAD_GRAYSCALE))
        arr.append(cv2.imread(dataset_path + "3.png", cv2.IMREAD_GRAYSCALE))
        
        self.data = np.array(arr)
        self.train_data = []
        self.test_data = []
        self.train_labels = []
        self.test_labels = []

        HEIGHT = self.data.shape[1]
        WIDTH = self.data.shape[2]
        
        file = open(dataset_path + "train.txt")
        triplets = file.read().split()

        for i in range(0, len(triplets)):
            triplets[i] = triplets[i].split(",")
        train_pixels = np.array(triplets, dtype=int)
        file.close()
   
        
        file = open(dataset_path + "test.txt")
        triplets = file.read().split()
        for i in range(0, len(triplets)):
            triplets[i] = triplets[i].split(",")
        test_pixels = np.array(triplets, dtype=int)
        file.close()
        
        for i in range(HEIGHT):
            for j in range(WIDTH):
                if train_pixels[i, j] != 0:
                    self.train_data.append(self.data[:, i, j])
                    self.train_labels.append(train_pixels[i, j])
                if test_pixels[i, j] != 0:
                    self.test_data.append(self.data[:, i, j])
                    self.test_labels.append(test_pixels[i, j])
        
        self.train_data = np.array(self.train_data)
        self.test_data = np.array(self.test_data)
        self.train_labels = np.array(self.train_labels)
        self.test_labels = np.array(self.test_labels)
        
        
    def get_train_data(self):
        return self.train_data

    def get_test_data(self):
        return self.test_data

    def get_train_labels(self):
        return self.train_labels

    def get_test_labels(self):
        return self.test_labels
