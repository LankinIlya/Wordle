import pandas as pd
import numpy as np

df = pd.read_csv('noun_list.csv')

a = df['ATM'].to_numpy()

b = []
for i in range(len(a)):
    if len(a[i]) == 5:
        b.append(a[i])

a = b
for i in range(len(a)):
    a[i] = '(' + str(i + 1) + ', \'' + a[i] + '\')'
    if i < len(a) - 1:
        a[i] += ',\n'
    else:
        a[i] += ';'

f = open('..\\src\\main\\resources\\db\\changelog\\changeset\\insert-words-1.sql', 'w')

f.write('--liquibase formatted sql\n\n')

f.write('--changeset shnirelman:insert-words-1\n\n')

f.write('TRUNCATE words;\n\n')

f.write('INSERT INTO words(id, text) VALUES\n')

for i in range(len(a)):
    f.write(a[i])
f.close()
