import os

files_list = []


def list_files_recursive(path='.'):
    for entry in os.listdir(path):
        if entry[0] == '.':
            return
        full_path = os.path.join(path, entry)
        if os.path.isdir(full_path):
            list_files_recursive(full_path)
        else:
            files_list.append(full_path)


# Specify the directory path you want to start from
directory_path = '../'
list_files_recursive(directory_path)

for file in files_list:
    f = open(file, 'r')
    lines = f.readlines()
    for line in lines:
        if len(line) > 80:
            print(file, "too long")
            exit(1)
print("All files are OK")