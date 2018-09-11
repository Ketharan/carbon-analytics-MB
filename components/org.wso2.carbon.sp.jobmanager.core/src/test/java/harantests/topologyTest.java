package harantests;

//import  org.wso2.carbon.sp.jobmanager.core;

import org.wso2.carbon.sp.jobmanager.core.appcreator.SPSiddhiAppCreator;
import org.wso2.carbon.sp.jobmanager.core.appcreator.SiddhiQuery;
import org.wso2.carbon.sp.jobmanager.core.topology.SiddhiQueryGroup;
import org.wso2.carbon.sp.jobmanager.core.topology.SiddhiTopology;
import org.wso2.carbon.sp.jobmanager.core.topology.SiddhiTopologyCreatorImpl;

import java.util.List;

public class topologyTest {
    public static void main(String[] args){
        SiddhiTopologyCreatorImpl topCreate = new SiddhiTopologyCreatorImpl();
        SPSiddhiAppCreator appCreator = new SPSiddhiAppCreator();

        String siddhiApp = "@App:name('EmailProcessor_Updated3') \n" +
                "@App:description('Email Processor benchmark for WSO2 Stream Processor 4.x.x') \n" +
                "@source(type='kafka',topic.list='email_processor',partition.no.list='0',threading.option='single.thread',group.id='group',bootstrap.servers='localhost:9092'," +
                "@map(type='json'))\n" +
                "@Import('inputEmailsStream:1.0.0')\n" +
                "define stream inputEmailsStream (iij_timestamp long, fromAddress string, toAddresses string, ccAddresses string, bccAddresses string, subject string, body string, regexstr string);\n" +

                "@Export('outputEmailStream:1.0.0')\n" +
                "define stream outputEmailStream (iij_timestamp long, fromAddress string, toAdds string, ccAdds string, bccAdds string, updatedSubject string, bodyObfuscated string);\n"+


                "@sink(type='log')\n" +
                "define stream AlertStream(iij_timestamp long, fromAddress string);\n"+


                "@name('query 11')\n"+
                "@dist(parallel='1', execGroup='filter')\n"+
                "from inputEmailsStream select iij_timestamp, fromAddress, toAddresses, ccAddresses, bccAddresses, subject, body insert into #filteredEmailStream1;\n"+

                "@name('query12') @dist(parallel='1', execGroup='filter') from #filteredEmailStream1 select iij_timestamp, fromAddress, emailProcessorBenchmark:filter(toAddresses) as toAdds, emailProcessorBenchmark:filter(ccAddresses) as ccAdds, emailProcessorBenchmark:filter(bccAddresses) as bccAdds, subject, body insert into #filteredEmailStream2;\n"+

                "@name('query21') @dist(parallel='1', execGroup='modify') from #filteredEmailStream2 select iij_timestamp, fromAddress, toAdds, ccAdds, bccAdds, subject, emailProcessorBenchmark:modify(body) as bodyObfuscated1 insert into #modifiedEmailStream1;";


        System.out.println("starting parsing");
        SiddhiTopology st = topCreate.createTopology(siddhiApp);
        System.out.println("parsing finished");
        List<SiddhiQueryGroup> queryGroupList = st.getQueryGroupList();
        int i =0;
        for (SiddhiQueryGroup queryGroup : queryGroupList){
            String septedApp = queryGroup.getSiddhiApp();
            List<SiddhiQuery> apps = appCreator.createApps("TestPlan1",queryGroup);
            for (SiddhiQuery app: apps){

                System.out.println(app.getApp());
                //break;
            }




            //System.out.println(septedApp);
            //System.out.println("\n\n\n");

        }
    }

}
