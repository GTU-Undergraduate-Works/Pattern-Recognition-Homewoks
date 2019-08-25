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



batch_size = 5

x_batch1 = x_train[0:5]
y_batch1 = y_true[0:5]

x_batch2 = x_train[5:10]
y_batch2 = y_true[5:10]





x_batch3 = x_train[10:15]
y_batch3 = y_true[10:15]


x_batch4 = x_train[15:20]
y_batch4 = y_true[15:20]

x_batch5 = x_train[20:25]
y_batch5 = y_true[20:25]

learning_rate = 0.001

model = Model.Model(0,0)

iteration = 0
while (model.calculate_cost(x_train, y_train) > 500.0):
    model.optimize(x_batch1, y_batch1, learning_rate)
    model.optimize(x_batch2, y_batch2, learning_rate)
    model.optimize(x_batch3, y_batch3, learning_rate)
    model.optimize(x_batch4, y_batch4, learning_rate)
    model.optimize(x_batch5, y_batch5, learning_rate)
    iteration += 1


print("Learning rate: ", batch_size, "  Number of iteration to convergence : ", iteration,
          "  Test Loss : ", model.calculate_cost(x_test, y_test))


batch_size = 10

x_batch1 = x_train[0:10]
y_batch1 = y_true[0:10]

x_batch2 = x_train[10:20]
y_batch2 = y_true[10:20]





x_batch3 = x_train[20:25]  + x_train[0:5]
y_batch3 = y_true[20:-5] + y_true[0:5]



iteration = 0
model = Model.Model(0,0)
while (model.calculate_cost(x_train, y_train) > 500.0):
    model.optimize(x_batch1, y_batch1, learning_rate)
    model.optimize(x_batch2, y_batch2, learning_rate)
    model.optimize(x_batch3, y_batch3, learning_rate)
    iteration += 1


print("Learning rate: ", batch_size, "  Number of iteration to convergence : ", iteration,
          "  Test Loss : ", model.calculate_cost(x_test, y_test))



batch_size = 15

x_batch1 = x_train[0:15]
y_batch1 = y_true[0:15]

x_batch2 = x_train[15:25] + x_train[0:5]
y_batch2 = y_true[15:25] + y_true[0:5]


iteration = 0
model = Model.Model(0,0)
while (model.calculate_cost(x_train, y_train) > 500.0):
    model.optimize(x_batch1, y_batch1, learning_rate)
    model.optimize(x_batch2, y_batch2, learning_rate)
    iteration += 1


print("Learning rate: ", batch_size, "  Number of iteration to convergence : ", iteration,
          "  Test Loss : ", model.calculate_cost(x_test, y_test))


batch_size = 20

x_batch1 = x_train[0:20]
y_batch1 = y_true[0:20]

x_batch2 = x_train[20:25] + x_train[0:15]
y_batch2 = y_true[20:25] + y_true[0:15]


iteration = 0
model = Model.Model(0,0)
while (model.calculate_cost(x_train, y_train) > 500.0):
    model.optimize(x_batch1, y_batch1, learning_rate)
    model.optimize(x_batch2, y_batch2, learning_rate)
    iteration += 1


print("Learning rate: ", batch_size, "  Number of iteration to convergence : ", iteration,
          "  Test Loss : ", model.calculate_cost(x_test, y_test))



iteration = 0
model = Model.Model(0,0)
while (model.calculate_cost(x_train, y_train) > 500.0):
    model.optimize(x_train, y_train, learning_rate)
    iteration += 1


print("Learning rate: ", batch_size, "  Number of iteration to convergence : ", iteration,
          "  Test Loss : ", model.calculate_cost(x_test, y_test))