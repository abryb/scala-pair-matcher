import sys
import time
from collections import defaultdict

"""
python prototype for pair matching problem

Usage:
python python_prototype.py <input-file> <output-file>

"""

start_time = time.time()

counts = defaultdict(int)
with open(sys.argv[2], 'w') as outputFile:
    with open(sys.argv[1]) as inputFile:
        while (line := inputFile.readline()) :
            n = int(line.rstrip())
            nPair = 12 - n
            if counts[nPair] > 0:
                counts[nPair] -= 1
                if n < nPair:
                    outputFile.write("(%d,%d)\n" % (n, nPair))
                else:
                    outputFile.write("(%d,%d)\n" % (nPair, n))
            else:
                counts[n] += 1

print("Process finished in %s" % (time.time() - start_time))
