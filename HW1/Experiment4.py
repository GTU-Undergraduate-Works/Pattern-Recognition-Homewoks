from numpy import *
import Model






def read_data_from_file(filename):
    file = open(filename, "r")
    file.readline()
    data = file.readlines()
    x_true, y_true = [], []
    for line in data:
        temp = line.split(",")
        x_true.append(int(temp[0]))
        y_true.append(int(temp[1][:-1]))
    return x_true, y_true




x_true, y_true = read_data_from_file("data.csv")

x_train = x_true[0:25]
y_train = y_true[0:25]
x_test = x_true[25:]
y_test = y_true[25:]



values = [0, 1, 2, 3, 4, 5, 6, 7, 8]



learning_rate = 0.001
momentum = 0

for value in values:
    iteration = 0
    model = Model.Model(value, value)
    while (model.calculate_cost(x_train, y_train) > 500.0):
        model.optimize(x_train, y_train, learning_rate, momentum)
        iteration += 1

    print("weight : ", value, "bias : ", value, "  Number of iteration to convergence : ", iteration,
          "  Train Loss : ", model.calculate_cost(x_train, y_train), "  Test Loss : ", model.calculate_cost(x_test, y_test))











