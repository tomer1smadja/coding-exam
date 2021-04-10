package com.cautils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cautils.models.Result;

public class Main {

    public static void main(String[] args) {
        String pathToInputFile = args[1];
        List<String> mailboxes;
        BufferedReader reader = new BufferedReader(new FileReader(pathToInputFile));
        boolean isEOF;
        do{
            String line = reader.readLine();
            mailboxes.add(line);
            isEOF = line == null;
        } while (!isEOF);
        MailboxVerifier verifier = MailboxVerifier.getInstance();
        Map<String, Result> results = new HashMap<>();
        for (String address : mailboxes){
            String rawResult = verifier.verifySingleMailbox(address);
            results.put(address, new Result(rawResult));
        }
        results.forEach((key, value) ->
                System.out.println(String.format("Mailbox %s is %s, raw result from API %s", key, value.getRawResult(), value.isValid())));
        /**
         * TODO: Code extension task #1:
         * We would like to start to save an execution history of this tool for future analysis.
         * Therefore we would like to export the results to a text file and to save it on the machine.
         * Refactor this code so instead of printing, the results will be save into a a file.
         * The content of the file should be in the following format (delimiter is for you choice, can be tab, space, comma or whatever):
         * mailbox  isValid?    Raw result
         * "tomer@gmail.com"  true  "{"result":"valid","reason":"accepted_email","disposable":"false","accept_all":"true","role":"false", ..... }"
         * @hint keep it simple - No fancy formatting...
         * The file should be located in the Desktop of the machine inside a directory named MailboxVerifierOutputs
         * The file name should be in the following scheme <datetime>_<user_id>_mailboxesReport.txt
         * For example, for userID = 100, the process that executed at Jan 31st 2021 at 00:00 will save a file in the following path:
         * ~/MailboxVerifierOutputs/202101310000_100_mailboxesReport.txt
         * */

        /**
         * TODO: Code extension task #2:
         * We got a request to process 1,000,000 of such emails, we are getting results pretty fast, however,
         * by the time we get near 100,000 (sometimes we pass this bar, and sometimes we don't) we get an OOM (Out Of Memory) Error
         * Fix this problem, so in the next time we'll get large amount of mailboxes (more than the mentioned bar), we won't fail,
         * and we'll be able to finish the task and to save the report file.
         * */

        /**
         * TODO: Code extension task #3 (bonus):
         * We have to speed things up!
         * We usually parse 50 emails in 1 minutes,
         * when we get large requests of more than 1,000 it takes us 20 minutes, and it's a lot time to wait for a report!
         * Find a why to speed things up. Hint! what is the most time consuming part of the code? how we can reduce this time?
         * */
    }
}
