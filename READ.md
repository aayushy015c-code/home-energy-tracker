# home-energy-tracker
Spring Boot 4 - Microservices portfolio project with Java

==========================
HOW ELECTRICAL USAGE WORKS?
==========================

Power(k or Kw): How much energy a devices uses per second
Energy(kWh): how much total energy the device uses over time
Price per kWh: how much you utility charges for each kilowatt-hour used

==========================
REQIUREMENTS
==========================

Track daily/hourly energy consumption per device or room
View trends over time
Ge alrets forn anoimalies
Receive AI-powered tips to reduce energy useage
Simulate or accept IoT/sensor data in real time

==========================
Assumptions
==========================

Users : 500,000
Derice per user: 5
Readings per deivce: 1 per minute
Each Reading -> 1 Ddatabase insert

===========================
CALCULATIONS
============================

Total number of devices = 500k * 5 = 2,500,000
Readings Per minute = 2,500,000 * 1 = 2,500,000
Readings per day = 2,500,000 * 60 * 24 = 3,600,000,000
Size of each readings = 72bytes
Storage required per day = 3,600,000,000 * 72 = 259GB/day

Write throughput = 2,500,000/60 = 41,600 inserts/second