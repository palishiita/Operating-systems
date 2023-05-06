#   Page Replacement algorithms
Counting number of pageVirtualMemory faults in each algorithm. Page fault occurs when the pageVirtualMemory demanded by the CPU is not present 
in the frames. virtual memory size (number of pages), physical memory size (number of frames)
the method of generating the sequence of pageVirtualMemory references should follow principle of locality of references.

## 1. First In First Out (FIFO)
When all frames are filled with pageVirtualMemory and, a new pageVirtualMemory is demanded by the CPU, and this new pageVirtualMemory is not present
in the frames (pageVirtualMemory fault counter increases) then using FIFO  the pageVirtualMemory which came first/earliest into the physical
memory is replaced by the new pageVirtualMemory. (traversing frame by frame until we reach frameSize move to 1st frame again until 
all pages in the string are finished)

## 2. Least Recently Used (LRU) 
When all frames are filled with pageVirtualMemory and, a new pageVirtualMemory is demanded by the CPU, and this new pageVirtualMemory is not present
in the frames (pageVirtualMemory fault counter increases) then using LRU (set up counter for recently used for pages that have 
already been allocated frames) and replace old pageVirtualMemory by new pageVirtualMemory. [checking older pages in queue]

## 3. Least Recently Used Approximation (ALRU) or second chance
Setting 1 and resetting 0 frameReferenceBit (uses fifo and frameReferenceBit)
When all frames are filled with pageVirtualMemory and, a new pageVirtualMemory is demanded by the CPU, if this new pageVirtualMemory is present
in the frames we assign 1 to frameReference bit if not present (pageVirtualMemory fault counter increases) we assign 0 to 
frameReference bit. We replace pages with 0 frameReferenceBit and using FIFO rule (pageVirtualMemory which came first and has 
0 reference bit is replaced).

## 4. Optimal pageVirtualMemory replacement (OPT)
When all frames are filled with pageVirtualMemory and, a new pageVirtualMemory is demanded by the CPU, if this new pageVirtualMemory is not present
in the frames using OPT we check future pages queue and look for pages with the least demand and is not used for longest 
duration of time. 
[checking future pages in queue]

## 5. Random 
When all frames are filled with pageVirtualMemory and, a new pageVirtualMemory is demanded by the CPU, if this new pageVirtualMemory is not present
in the frames (pageVirtualMemory fault counter increases) randomly choose a frame [using Random class] and replace it by new pageVirtualMemory.