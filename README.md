60-480 - Noise analyzer
============================
Server waits for requests from clients to get the audio levels in a fixed location
using a microphone. We return a json with information such as amplitude, decibels,
etc.

Public API function calls:

  - public String getNoise (int seconds)
    - Analyzes audio (in real time) for that many seconds.

  - public String getNoiseInterval (int seconds, int numIntervals)
    - Analyzes audio (in real time) for that many seconds, for that
  many intervals.

Both functions return a valid JSON string with the following attributes:
minAmplitude, maxAmplitude, meanAmplitude, rmsAmplitude, db, rFrequency, meanNorm, maxDelta, minDelta, meanDelta, rmsDelta, and midlineAmplitude.

Interpretation of attributes is left to the client.

Description of files:
======================
__MultipleClients.java:__ contains implementation using both microphone and robot APIs.

__PiInfo.java:__ Our public API interface

__PiInfoServer.java:__ responsible for creating and running server process.

__PiInfoService.java:__ contains implementations of our APIs.

__PiSampleClient.java:__ small sample client program using our API.

__Policy:__ RMI policy.

__RaspiRobotInterface.java:__ robots public API.

__SoundReporter.java:__ class responsible for recording audio and parsing it.

__java-json.jar:__ Java JSON library. Optional.

__start_client.bat:__ sample program script used to run client on Windows.

__start_client.sh:__ sample program script used to run client on Unix machines.

__start_mult_clients.sh:__ sample program script for mic + robot.

__start_server.sh:__ script to create server process.


How to Run it
===============

Run the following on your command line:
  - git clone https://github.com/velazqua/480-pi-project.git

Compile all java files: (works in Unix)
  - javac -cp ".:./java-json.jar" *.java

To run the server:
  - ./start_server.sh (Unix)

Run sample client program:
  - ./start_client.sh (Unix) (Tested on Ubuntu. Works)
  - ./start_client.bat (Windows) (Untested)

Run client program that interacts with microphone and robot:
  - ./start_mult_clients.sh (Unix)

Note: IP is hardcoded into the sampleClient program. Our IP is 137.207.74.151 with port number 2024. 
Our Pi is connected to the CS-WL-2 network.
