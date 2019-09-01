"""
authors: Alec Bird, Rui Tang, Jack Schroer, Chao Zhai
course: CSE211
program purpose: To read lines from a file and put specific info into a dictionary
Date: 2/22/19
Homeowork1 - running Python 3.6.6
"""
from functools import reduce


def getInfo(key, library):
    """
    Prints the dictonary info for a user specified key
    """
    value = library[key]
    ty = value[len(value)-1]
    sep = " "
    if ty == "Book":
        print("{}\t{}, {}, {}, {}.".format(key, sep.join(value[0]), sep.join(value[1]), sep.join(value[2]), value[3]))
    elif ty == "Journal":
        print("{}\t{}, {}, {}:{}({}), {}.".format(key, sep.join(value[0]), sep.join(value[1]), sep.join(value[2]), value[4], value[5], value[3]))
    else:
        print("{}\t{}, {}, {}, {}, {} {}, {}.".format(key, sep.join(value[0]), sep.join(value[1]), sep.join(value[2]),sep.join(value[5]), sep.join(value[3]), value[4], sep.join(value[6])))


def authorFun(names):
    """
    Modifies order of list of author names
    """
    newNames = []
    firstName = names[0]
    names[0] = names[1]
    names[1] = firstName
    newNames.append(names[0])
    newNames.append(names[1])
    if len(names) > 2:
        for i in range(2, len(names)-1, 2):
            newNames.append(names[i] + " " + names[i+1])
    return newNames
def main():
    """
    Reads a file and prints the elements in specific order
    """
    # opens file, needs to be in location where program is
    file = open('Step3Data.txt', 'r')
    # read each line of the file
    library = {}
    bookInfo = []
    key = ""
    bool = 1
    typeZero = ""
    for line in file.readlines():
        data = line.split()
        if data[0] == "Book" or data[0] == "Journal" or data[0] == "Conference":
            type = data[0]
            if typeZero == "":
                typeZero = type
            if key != "":
                bookInfo.append(typeZero)
                typeZero = type
                library[key] = bookInfo
                bookInfo = []
        if data[0] == "Book":
            bool = 1
        if data[0] == "Journal":
            bool = 2
        if data[0] == "Conference":
            bool = 3
        elif data[0] == "Key:":
            key = data[1]
        elif data[0] == "Author:":
            bookInfo.append(authorFun(data[1:]))
        elif data[0] == "Title:":
            bookInfo.append(data[1:])
        elif data[0] == "Publisher:":
            bookInfo.append(data[1:])
        elif data[0] == "Date:":
            if bool == 1 or bool == 2:
                bookInfo.append(data[1])
            elif bool == 3:
                bookInfo.append(data[1:3])
                bookInfo.append(data[3])
        elif data[0] == "Volume:" and bool == 2:
            bookInfo.append(data[1])
        elif data[0] == "Number:" and bool == 2:
            bookInfo.append(data[1])
        elif data[0] == "Location:" and bool == 3:
            bookInfo.append(data[1:])
        elif data[0] == "Pages:" and bool == 3:
            bookInfo.append(data[1:])
        elif data[0] == "Conference:" and bool == 3:
            bookInfo.append(data[1:])

    userKey = input("Enter a key: ")
    if userKey in library:
        getInfo(userKey, library)
    else:
        print("Invalid Key")
        
    
   
if __name__ == "__main__":
    main()
    