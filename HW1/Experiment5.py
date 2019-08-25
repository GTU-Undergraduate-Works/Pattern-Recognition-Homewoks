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




learning_rate = 0.001
momentums = [0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1]

for momentum in momentums:
    iteration = 0
    model = Model.Model()
    while (model.calculate_cost(x_train, y_train) > 500.0):
        model.optimize(x_train, y_train, learning_rate, momentum)
        iteration += 1

    print("Momentum: ", momentum, "  Number of iteration to convergence : ", iteration,
          "  Train Loss : ", model.calculate_cost(x_train, y_train), "  Test Loss : ", model.calculate_cost(x_test, y_test))











