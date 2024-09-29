import os

files_list = []
forbidden_prefixes = ["."]
forbidden_files = ["gradlew.bat", "gradle"]

def list_files_recursive(path='.'):
    for entry in os.listdir(path):
        for prefix in forbidden_prefixes:
            if entry[:len(prefix)] == prefix:
                return
        for file in forbidden_files:
            if entry == file:
                return

        full_path = os.path.join(path, entry)
        if os.path.isdir(full_path):
            list_files_recursive(full_path)
        else:
            files_list.append(full_path)


# Specify the directory path you want to start from
directory_path = '../'
list_files_recursive(directory_path)

any_errors = False

for file in files_list:
    f = open(file, 'r')
    lines = f.readlines()
    for line in lines:
        if len(line) > 80:
            print(file, "too long")
            any_errors = True
            break

if any_errors:
    exit(1)

print("All files are OK")