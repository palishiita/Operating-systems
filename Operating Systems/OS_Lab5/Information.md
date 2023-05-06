# OPERATING SYSTEM LAB 5

### Processor scheduling or allocation in distributed systems (processor assignment for a process)

##### file:///C:/Users/Nandita/Desktop/Processor%20allocation.pdf 
##### file:///C:/Users/Nandita/Desktop/PROCESSOR%20ALLOCATION%20-%20task5.pdf

- There are N identical (machines) in a distributed system 
- N = about 50-100 and a long series of tasks (processes) to be performed 
  (choose the parameters yourself)
- on each of them there is a number of new tasks (processes) executed, each task consumes a random fraction of 
  processor capacity (like 20%, 40% etc.). And new tasks emerge randomly over time.
- eventually some processors get overloaded (sum of their process requirements exceeds 100%), while others are underloaded.
- we expect our processors in a distributed system to be equally used, so we use the above algorithms to equalize processor load.
  
- The criteria to evaluate the algorithms are:
1. How good they are (mean and variation of processors load)
2. What is communication cost (how many messages were sent)
3. What is migration cost (how many tasks migrated from processor to
   processor – because this requires transferring code and data)
   
   Our task is to look at how the algorithms perform under different assumptions 
   (size of the system – N, intensity of appearance of new tasks).

   The min/max thresholds are processor loads which trigger action when exceeded 
   (for example, in the description we read: „…initiated by an
   overloaded sender, is one initiated by an underloaded receiver” – we
   assume, for example, the following: sender is overloaded when it uses over 95% of the processor capacity, 
   receiver is underloaded when it uses under 30% of the processor capacity).

**In each simulation, report as the result:**
- Average CPU load (decide, reasonably, how it will be calculated).
- Average deviation from the value in point A (CPU load).
- Number of load queries (requests sent)
- process migrations (moves).

**Simulate the following allocation strategies: Given that a task appears on processor x.**

**User should be able to specify (change) p, r, z, N values**


### concepts
- processor allocation (load distribution) determine which process is assigned to which processor. (machine which runs the process)
"only concern is when a process will get the processor"

- Processor (hardware)  is an integrated electronic circuit that performs the calculations that run a computer. 

- we write our computer programs in a text file and when we execute this program, 
  it becomes a process (software) which performs all the tasks mentioned in the program.

- CPU load (max, min, current): 
  number of processes that: are using CPU (CPU time);  want to use CPU; queued up processes ready to use CPU.

- CPU time is the exact amount of time that the CPU has spent processing data for a specific process. 
Process do not use the processor 100% of the time that they are running; some of that time is spent on I/O operations 
and fetching and storing data on the RAM.

- Process migration:Process migration is defined as the transfer of a process between different nodes connected 
  by a network. (current node to destination node).

- Load balancing tries to ensure that every processor in the system does almost the same amount of work at any
  time. Thus, the load of each of the nodes will be equal in the system at any point of time.
  Processes have to be migrated from one machine to another even at the middle of execution

- The motivations for process migration are Dynamic Load Balancing. It allows processes to take advantage of less loaded
  nodes by migrating from overloaded ones


a big number of queries = communication delays, which slows down the execution. 
But on the other hand, a lot of queries sent dynamically, during the duration of execution mean, that the CPU will adjust 
it's work dynamically to the needs of the system, so the avg CPU load should be the smallest