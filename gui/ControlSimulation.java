package gui;

import org.xml.sax.InputSource;
import java.net.URL;
import java.net.MalformedURLException;
import java.applet.AppletContext;
import controller.SqlCommunicator;
import controller.TUGInformations;
import controller.LevelHandler;
import controller.LabyrinthField;
import controller.IntStringMap;
import controller.LabyrinthXpertMode;

public class ControlSimulation
{
	private ControllerGuiCommunicator controller_gui_communicator_;
	private ControlLevel control_level_;
	private MenuePanel menue_panel_;
	private MenueExercisePanel menue_exercise_panel_;
	private MenueExamPanel menue_exam_panel_;
	private int xpert_count_;
	private String source_;
	private TUGLabyrinth laby_;
	private LevelHandler level_handler_;
	private IntStringMap mark_array_;
	private LabyrinthXpertMode xpert_mode_;

	public ControlSimulation(MenueExercisePanel menue_panel, LevelHandler level_handler, IntStringMap mark_array)
	{
		menue_exercise_panel_=menue_panel;
		level_handler_=level_handler;
		mark_array_=mark_array;
		menue_exercise_panel_.setControlSimulation(this);
		xpert_count_=0;
		xpert_mode_=new LabyrinthXpertMode(mark_array);
	}

	public ControlSimulation(MenueExamPanel menue_panel, LevelHandler level_handler, IntStringMap mark_array ,TUGLabyrinth laby)
	{
		laby_=laby;
		level_handler_=level_handler;
		mark_array_=mark_array;
		menue_exam_panel_=menue_panel;
		xpert_mode_=new LabyrinthXpertMode(mark_array);
		menue_exam_panel_.setControlSimulation(this);

	}

	public ControlSimulation(MenuePanel menue_panel, LevelHandler level_handler, IntStringMap mark_array)
	{
		menue_panel_=menue_panel;
		level_handler_=level_handler;
		mark_array_=mark_array;
		xpert_mode_=new LabyrinthXpertMode(mark_array);
		menue_panel_.setControlSimulation(this);
	}

	public void setCommunicationElements(ControllerGuiCommunicator controller_gui_communicator, ControlLevel control_level)
	{
		controller_gui_communicator_=controller_gui_communicator;
		control_level_=control_level;
	}

	public void addEmptyRule()
	{
		controller_gui_communicator_.addEmptyRule();
	}

	public void addRuleXpert()
	{
		controller_gui_communicator_.newRuleXpert();
	}

	public void addLabXpert(int level)
	{
		xpert_mode_.setLabyrinthField(level_handler_.getLabyrinthField());
		String current = xpert_mode_.getCurrentLabyrinthXML(level);
		String generating = xpert_mode_.getLabyrinth(level);
		LabyrinthXpertFrame labyrinth_xpert_frame = new LabyrinthXpertFrame(current, this);
		SqlCommunicator.add_log("Labyrinth Xpert Frame geöffnet", 16);
		xpert_count_++;
		if (menue_panel_!=null)
		{
			menue_panel_.incrementXpertCount();
			menue_panel_.refreshList(0);
		}
	}

	public void xpertLabyrinth(String source) //---------Übernehmen
	{
		source_=source;
		SqlCommunicator.add_log("Labyrinth vom Xpert Frame neu eingelesen", 16);
		SqlCommunicator.flush_log();
		control_level_.drawNewLabyrinth(source);
	}

	public void xpertLabyrinthClosed()
	{
		xpert_count_--;
		SqlCommunicator.add_log("Labyrinth Xpert Frame geschlossen", 16);
		if (menue_panel_!=null)
		{
			menue_panel_.decrementXpertCount();
			menue_panel_.refreshList();
		}
	}

	public void drawNewLabyrinth(int level) //-----------select change
	{
		SqlCommunicator.flush_log();
		if (level==0)
		{
			control_level_.drawNewLabyrinth(source_);
		}
		else
		{
			control_level_.drawNewLabyrinth(level);
		}
	}

	public void drawNewLabyrinthSetTimer(int level) //-----------select change
	{
		SqlCommunicator.flush_log();
		if (level==0)
		{
			control_level_.drawNewLabyrinth(source_);
		}
		else
		{
			control_level_.drawNewLabyrinth(level);
		}
		if (menue_exam_panel_!=null)
			menue_exam_panel_.performNextButton(0);
	}

	public void printHelp()
	{
		System.out.println("Help");
    	try
    	{
      	//context_.showDocument(new URL("http", code_base_), "_blank");
      	//context_.showDocument(new URL("http://", code_base_.toString(), "HelpWindow/inhalt.html"), "_blank");
      	SqlCommunicator.add_log("Hilfe aufgerufen", 16);
      	TUGInformations.getAppletContext().showDocument(new URL(TUGInformations.getCodeBase(), "HelpWindow/index.php"), "_blank");
    	}
    	catch (MalformedURLException ex)
    	{
	    }
	}

	public void levelFinished()
	{
		if (menue_exam_panel_!=null)
		{
			menue_exam_panel_.nextLevel();
		}
	}

	public void gameFinished()
	{
		laby_.drawFinish();
	}
}