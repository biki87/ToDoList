package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Custom implementation of ArrayAdapter for ToDoList
 */
public class ToDoAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> values;

    public ToDoAdapter(Context context, List<String> values){
        super(context, android.R.layout.simple_list_item_1, values);
        this.context = context;
        this.values = values;
    }

    //custom implementation of getView method
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView task = new TextView(context);
        task.setText(values.get(position));
        task.setTextSize(30);
        //alternate colors
        if (position%2 == 0){
            task.setTextColor(Color.RED);
        }
        else{
            task.setTextColor(Color.BLUE);
        }
        return task;
    }
}
