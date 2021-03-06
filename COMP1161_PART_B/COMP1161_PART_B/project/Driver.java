package project;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
//import project.Tester;
public class Driver {
    WorkArea workArea = new WorkArea();

    
	public static void main(String[] args) {
		//Tester.main(args);;
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		//Venue[] venues = new ArrayList<Venue>();
		//populateVenues(mny);
		int selCase =-1;
		EntryScreen e= new EntryScreen();
		ReportScreen r= new ReportScreen();
		WorkArea work = new WorkArea();
		SystemInfo sys = new SystemInfo();
		// s;

		char mchoice = 'c';
		boolean listdata = true;
		String menu="";
		String stuId="";
		
		while (mchoice!='X')
		{
			  try
              {
                  Scanner sc = new Scanner(new File("id.txt"));
                  stuId = sc.nextLine();
                  sc.close();
              }
              catch (IOException ioe){}

			String menuOptions = "-----User Data";
			if (selCase==-1)
				 menuOptions = "---"+menuOptions +"--------\n";
			else
				menuOptions+=" Case "+ selCase+"-----------\n";
			
			menuOptions+="[P]romoter data\n[V]enue management\n[M]inistry interface\n";
			menuOptions+="[L]oad test case\n[S]ave test case\n[T]est all cases\n";
			menuOptions+="-------System settings--------\n[I]D:";
			
            if (stuId.length()==0)
            	menuOptions+="Not yet set.\n";
            else
            	menuOptions+=stuId+"\n";

			/*menuOptions+="User directory:"+System.getProperty("user.dir")+"\n";
			menuOptions+="Test case folder:"+sys.getTestCaseFolder()+"\n";
			menuOptions+="Code folder:"+sys.getCodeFolder()+"\n";*/
			menuOptions+="Javadoc folder:"+sys.getJavaDocFolder()+"\n";
			menuOptions+="Update s[y]stem settings\n";
			menuOptions+="[A]utodetect system settings\n";
			menuOptions+="E[x]it";
			
			System.out.println(menuOptions);
			if (!(System.getProperty("user.dir")+"\\cases").equals(sys.getTestCaseFolder()))
			{
				System.out.println("WARNING:::POSSIBLE TEST CASE LOCATION MISMATCH:" );
				System.out.println("Please move the cases folder from ");
				System.out.println(sys.getTestCaseFolder()   + " to ");
				System.out.println(System.getProperty("user.dir")+ " then hit A+<Enter> to detect locations");
			}			
			
			menu = scan.next().toUpperCase();
			mchoice = menu.charAt(0);
			switch(mchoice)
			{
			case 'P':{
				work.promoters = e.managePromoters(scan,  work.promoters,  work.mny, work.venues);
				break;
			}
			case 'V':{
				work.venues = e.manageVenues(scan, work.venues);
				break;
			}
			case 'M':{
				work.mny.showPublicInfo(scan,work);
				break;

			}
			case 'L':{
				System.out.println("Please enter the number of the test case to be loaded[0..11]:");
				int caseNo = Integer.parseInt(scan.next());
				work.loadTestCase(caseNo,scan);
				break;

			}
			case 'T':{
				Tester.runCases(work,sys);
				// s = e.editSystemInfo(s);
				break;

			}
			case 'S':{
				Tester.saveTest(scan, work);
				
				
				// s = e.editSystemInfo(s);
				break;

			}
			case 'Y':{
				 sys.readFromScreen(scan);
				break;

			}
            case 'I':
            {
                System.out.println("Please enter your student ID number:");
                String stuid = scan.next();
                
                try{    
                    PrintStream p=new PrintStream(new FileOutputStream("id.txt"));
                    p.println(stuid);
                    p.close();
                }
                catch(IOException ioe){}
                break;
            } 
            case 'A':
            {
                File f = new File("Sysinfo.txt");
                f.delete();
                sys = new SystemInfo();           }

			}
		}
		}

	
}
