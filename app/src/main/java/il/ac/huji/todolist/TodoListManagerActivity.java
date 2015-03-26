package il.ac.huji.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemLongClickListener;
import java.util.List;
import java.util.ArrayList;


public class TodoListManagerActivity extends ActionBarActivity {

    private List<String> toDoList;
    private ToDoAdapter toDoAdapter;
    //listener for item delete dialog
    private OnItemLongClickListener taskLongClickHandler = new OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView parent, View v, int position, long id) {
            AlertDialog deleteTask = createDeleteDialog(position);
            deleteTask.show();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create starting view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        //instantiate list and adapter
        toDoList = new ArrayList();
        toDoAdapter = new ToDoAdapter(this, toDoList);

        //bind adapter to list view
        ListView toDoListView = (ListView)findViewById(R.id.lstToDoItems);
        toDoListView.setAdapter(toDoAdapter);

        //set listener for long click (item delete dialog)
        toDoListView.setOnItemLongClickListener(taskLongClickHandler);
    }

    //creates a delete dialog for a given list item
    private AlertDialog createDeleteDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //title of the dialog is content of task
        builder.setTitle(toDoList.get(position));

        //listener for when user approves item deletion
        builder.setPositiveButton(R.string.delete_item, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //remove the item from the list and update the view
                toDoList.remove(position);
                toDoAdapter.notifyDataSetChanged();
            }
        });

        return builder.create();
    }

    //create the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //respond to user clicking on menu > add
    public boolean onOptionsItemSelected(MenuItem item) {
        //there's only a single menu item this time, but generally there should be a switch
        //statement here to figure out which menu item was selected

        //get the EditText
        EditText edtNewItem = (EditText)findViewById(R.id.edtNewItem);

        //add the text from the EditText to the list
        toDoList.add(edtNewItem.getText().toString());

        //update ListView that list has changed
        toDoAdapter.notifyDataSetChanged();

        //clear the EditText
        edtNewItem.setText("");
        return true;
    }
}
