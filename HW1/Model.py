import random
from _decimal import Decimal


class Model:

    def __init__(self, weight=None, bias=None):
        if weight is None:
            self.weight = random.random()
        else:
            self.weight = weight

        if bias is None:
            self.bias = random.random()
        else:
            self.bias = bias

        self.delta_weight = 0
        self.delta_bias = 0

    def predict(self, x):
        prediction = []
        for i in x:
            prediction.append(round(i * self.weight + self.bias, 2))
        return prediction



    def calculate_cost(self, x, y_true):
        assert len(x) == len(y_true), "Length of vectors are not same."
        totalError = 0.0
        y_predict = self.predict(x)
        for i in range(0, len(x)):
            totalError += float(Decimal(y_true[i] - y_predict[i]) ** 2)
        return totalError / float(len(x))


    def calculate_weight_gradient(self, x, y_true):
        assert len(x) == len(y_true), "Length of vectors are not same."
        totalGradient = 0.0
        N = len(x)
        y_predict = self.predict(x)
        for i in range(N):
            totalGradient += -(2 / N) * x[i] * (y_true[i] - y_predict[i])
        return round(totalGradient,2)

    def calculate_bias_gradient(self, x, y_true):
        assert len(x) == len(y_true), "Length of vectors are not same."
        totalGradient = 0.0
        N = len(x)
        y_predict = self.predict(x)
        for i in range(N):
            totalGradient += -(2 / N) * (y_true[i] - y_predict[i])
        return round(totalGradient,2)

    def optimize(self, x, y_true, learning_rate, momentum=0):
        assert len(x) == len(y_true), "Length of vectors are not same."
        weight_gradient = self.calculate_weight_gradient(x, y_true)
        bias_gradient = self.calculate_bias_gradient(x, y_true)

        self.delta_weight = (momentum * self.delta_weight) - round(learning_rate * weight_gradient, 2)
        self.delta_bias = (momentum * self.delta_bias) - round(learning_rate * bias_gradient, 2)

        self.weight = self.weight + self.delta_weight
        self.bias = self.bias + self.delta_bias





