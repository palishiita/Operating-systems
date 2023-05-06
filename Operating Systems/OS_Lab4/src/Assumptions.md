In operating systems, whenever a new page is referred and not present in memory, page fault occurs and Operating System
replaces one of the existing pages with newly needed page.

**Assumptions:** 
- Input from user: frames, pages, processes, requests "n"
- Each process has to have "n" requests (reference)  
- Principle of locality is necessary to meet
- Simulations should be carried out for page replacement algorithms: OPT, ALRU, least LRU, FIFO, random
- For parallelization, test the allocation of processes: equal, proportional, working set
- Global and local replacement methods
- We cannot allocate more than total number of available frames. We must allocate at least a minimum number of frames.
- [Question] How do frame allocation strategies affect performance (number of page faults - globally, for each process)?



**METHODS OF FRAME ALLOCATION**
1. **Equal**
- if there are m frames and n processes give 1 process **m/n frames**

3. **PROPORTIONAL**
   Processes that have bigger requirements will be allocated more frames to hold sufficient pages inside the frames.
- a = s/S × m 
- a = allocated frames, a > minimumNumberOfFrames, with sum not exceeding m 
- s = size of virtual memory for a process
- S = sum of (s) i.e. sum of all size of virtual memory for all processes 
- m = total frames (input by user)

4. **PAGE FAULT FREQUENCY**
memory pool = logical division of main memory
- Per-process replacement; at any given time, each process is allocated a fixed number of physical page frames.
- Monitor the rate at which page faults are occurring for each process. 
- If the rate gets too high for a process, assume that its memory is overcommitment; increase the size of its memory pool. 
- If the rate gets too low for a process, assume that its memory pool can be reduced in size. 
- If the sum of all memory pools don't fit in memory, deactivate some processes.


5. **WORKING SET**: prevent thrashing!!
how? explained: https://web.stanford.edu/~ouster/cgi-bin/cs140-winter12/lecture.php?topic=thrashing#:~:text=Working%20Sets%3A%20conceptual%20model%20proposed,prevent%20this%20process%20from%20thrashing.
- DELTA = workingSetWindow (we always take maximum of delta number of pages in memory)



**METHODS OF Page replacement**
1. **FIFO**
- operating system keeps track of all pages in the memory in a queue, the oldest page is in the front of the queue. 
- When a page needs to be replaced page in the front of the queue(oldest) is selected for removal.

2.**LRU**
- any page that has been unused for a longer period of time than the others is replaced [checking past pages in 
reference string].

3. **ALRU**
- Setting 1 and resetting 0 frameReferenceBit (uses fifo and frameReferenceBit). 
- When all frames are filled with page and, a new page reference is demanded by the CPU, if this new page reference is 
present in the frames we assign 1 to frameReferenceBit 
- If not present we assign 0 to frameReference bit and page fault counter increases. 
- We replace pages with 0 frameReferenceBit and using FIFO rule i.e. page which came first and has 0 reference bit is 
replaced first.

4. **OPT**
- we check future page references and look for pages with the least demand (not used for long duration) and this page is 
replaced. [checking future pages in reference string]

5. **RAND**
- randomly choose a page in frame and replace it with new page in reference string.



**REPLACEMENT METHODS** (we are choosing global replacement and justifying our choice!)
1. **Global replacement** 
   Global replacement allows a process to select a replacement frame from the set of all frames, even if that frame is
   currently allocated to some other process.

2. **Local replacement**
   Local replacement requires that each process select from only its own set of allocated frames.