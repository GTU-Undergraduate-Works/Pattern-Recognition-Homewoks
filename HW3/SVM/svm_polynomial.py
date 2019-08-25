import Dataset
import numpy as np
from sklearn import svm
from operator import truediv
from sklearn.model_selection import GridSearchCV
from sklearn import metrics

def svc_parameter_selection(X, y, nfolds):
    Cs = Cs = [0.001, 0.005, 0.01, 0.05, 0.1, 0.5, 1]
    degrees = [0, 3, 6, 9, 12, 15]
    param_grid = {'C': Cs, 'degree': degrees}
    grid_search = GridSearchCV(svm.SVC(kernel='poly', gamma='scale'), param_grid, cv=nfolds, iid=False, verbose=1)
    grid_search.fit(X, y)
    grid_search.best_params_
    return grid_search.best_params_


def AA_andEachClassAccuracy(confusion_matrix):
    counter = confusion_matrix.shape[0]
    list_diag = np.diag(confusion_matrix)
    list_raw_sum = np.sum(confusion_matrix, axis=1)
    each_acc = np.nan_to_num(truediv(list_diag, list_raw_sum))
    average_acc = np.mean(each_acc)
    return each_acc, average_acc


dataset = Dataset.Dataset()

train_data = dataset.get_train_data()
test_data = dataset.get_test_data()
train_labels = dataset.get_train_labels()
test_labels = dataset.get_test_labels()



print("Starting grid search to find best parameters for SVM using cross validation")
best_params = svc_parameter_selection(train_data, train_labels, 5)


c = best_params['C']
degree = best_params['degree']


classifier = svm.SVC(kernel='poly', C=c, gamma='scale', degree=degree)
classifier.fit(train_data, train_labels)
prediction =  classifier.predict(test_data)

overall_acc = metrics.accuracy_score(prediction, test_labels)
kappa = metrics.cohen_kappa_score(prediction, test_labels)
confusion_matrix = metrics.confusion_matrix(prediction, test_labels)
each_acc, average_acc = AA_andEachClassAccuracy(confusion_matrix)

print("C :", c)
print("Degree :", degree)
print("Overall Accuracy of testing sapmles : ", overall_acc)
print("Average Accuracy of testing samples : ", average_acc)
print("Each class accuracy of testing samples : ", each_acc)
print("Kappa statistics of testing samples : ", kappa)
print("-----------------------------------------------------------")
	
