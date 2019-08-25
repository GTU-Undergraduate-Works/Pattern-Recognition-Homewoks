import matplotlib.pyplot as plt
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



learning_rate = 0.00001
iterations = 10
momentum = 0
model = Model.Model(0, 0)



validation_loss_graph = []
train_loss_graph = []


iter = []

for i in range(30):
    model.optimize(x_train, y_train, learning_rate, momentum)
    train_loss =  model.calculate_cost(x_train, y_train)
    test_loss = model.calculate_cost(x_test, y_test)
    train_loss_graph.append(train_loss)
    validation_loss_graph.append(test_loss)
    iter.append(i)
    print ("Train Loss : ", train_loss, "   Test Loss : ", test_loss)



plt.plot(iter,train_loss_graph)
plt.xlabel('Iterations')
plt.ylabel('Loss')
plt.title('Train Loss Graph')
plt.show()


plt.plot(iter,validation_loss_graph)
plt.xlabel('Iterations')
plt.ylabel('Loss')
plt.title('Validation Loss Graph')
plt.show()










