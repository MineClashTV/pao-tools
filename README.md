# pao-tools

For the two remaining pixelanarchy players among us.

![Preview](preview.png)

## Tools:

- Converting images to the PAO color palette
- Floyd-Steinberg dithering
- Scaling images down
- Splitting images by their colors
- Generating proper predither images using a box blur algorithm

## Usage

This program requires Java 15 and offers no graphical interface. As of now, you have to use the interactive CLI.

1. Make sure you have JRE / JDK 15 installed. Check with the command ```java --version```.

If it shows an older version, then install the ```jdk-openjdk``` package in Linux. Windows users may get it from https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot

2. In a terminal, cd to the directory where the jar file is located and type ```java -jar pao-tools*.jar```

3. Follow the instructions, should be self-explanatory. Paths can be relative (e.g. "image.png") or direct (e.g. "/home/user/image.png")

## Build and dev dependencies

Image scaling functionality is based on the [imgscalr 4.2](https://github.com/rkalla/imgscalr) library.