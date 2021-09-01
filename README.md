Błażej Rybarkiewicz

## Toy Scala application for finding pairs of integers whose sum equals to given number.

### What it does:

Given list of integers it can match pairs whose sum equals to given number.

`1,0,5,10,6,11,5` => (find pairs whose sum is 11) => `(1,11),(5,6),(0,11)`

### Usage
```
scala-pair-matcher <input.csv> <output.csv> [--pair=NUM] [--min=NUM] [--max=NUM]
   Arguments:
     input.csv     input file in csv format
     output.csv    output file in csv format
   Options:
     --pair=NUM    make pairs whose sum is NUM          (default 0)
     --min=NUM     validate if input numbers are >= NUM (default Int.MinValue)
     --max=NUM     validate if input numbers are <= NUM (default Int.MaxValue)
     --help        print help/usage```

```

### Running on host
```bash
sbt assembly
java -jar target/scala-2.13/scala-pair-matcher-assembly-0.1.jar data/input.csv data/output.csv --pair=12 --min=0 --max=12
```

### Running with docker

##### Building:
```bash
docker build -t abryb/scala-pair-matcher .
```

##### Running:
In order to read from and write to file we have to mount directory. Given data is in `data` directory:
```bash
docker run --rm -it -v $PWD/data:/data abryb/scala-pair-matcher input.csv output.csv --pair=12 --min=0 --max=12
```

### Supported input format

Only supported input format is csv file of integers, for example:
`input.csv`
```
4
7
3
6
9
4
``` 