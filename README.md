#Polling File reader.

Files are received containig data to be processed.  The files needs to be procesed as a pair.

\<job-id>_READY.json  and \<job_id>_META.xml

In order to process the files both files need to be received.  The followng Spring Integration flow will be used.

1. Receive files using inbound file channel adapter.

2. Aggregator will be used to group the files together.  
    The <job_id> will be the correlation id,   and the release strategy will be when we have a valid pair.
    
3. Service Activator will do the work processing the pair of files.

4. Splitter will split the ArrayList of files into individual files.

5. Outbound file channel adapter will move the file to a new a completed location.


#Additional info:
 
a dispatcher was added to the fileProcessingChannel, this allows the service activator
to process released message groups in parallel using a spring executor.


