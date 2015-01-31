package com.app.lukas.llxcodeeditor;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    String Tag = "Main";
    HashMap<String,String> Variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MultiAutoCompleteTextView editText = (MultiAutoCompleteTextView) findViewById(R.id.editText);
        editText.setTokenizer(new MethodTokenizer());
        editText.setText("", TextView.BufferType.SPANNABLE);
        Variables = new HashMap<>();
        Variables.put("LL","LL");
        Variables.put("Android","Android");
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(Tag,s.toString());
                if(count!=0) {
                    checkForVariables(editText,start);
                }
                Log.d(Tag, "Types: "+Arrays.deepToString(Variables.entrySet().toArray()));int pos = editText.getSelectionStart();

                Layout layout = editText.getLayout();
                int line = layout.getLineForOffset(pos);
                int baseline = layout.getLineBaseline(line);
                int ascent = layout.getLineAscent(line);
                float y = baseline + ascent;
                Space anchor = (Space)findViewById(R.id.anchor);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) anchor.getLayoutParams();
                params.setMargins(0,Math.round(y),0,0);
                anchor.setLayoutParams(params);
                Log.d(Tag,"Y: "+y);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0)return;
                char lastChar = s.charAt(s.length()-1);
                if(lastChar=='.')
                {
                    String variable = "";
                    if(s.length()>=2) {
                        int y = s.length() - 2;
                        while (y>=0&&(Character.isDigit(s.charAt(y)) || Character.isLetter(s.charAt(y)) || Character.isSpaceChar(s.charAt(y)))) {
                            variable += s.charAt(y);
                            y--;
                        }
                        variable = variable.trim();
                    }
                    if(Variables.containsKey(variable)){
                        Log.d(Tag,"Variable: "+variable);
                        try {
                            HashMap<String,String> methods = ((HashMap)Methods.class.getField(Variables.get(variable)).get(null));
                            String[] methodNames = methods.keySet().toArray(new String[methods.size()]);
                            Log.d(Tag,"methodNames: "+Arrays.deepToString(methodNames));
                            ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdownitem, methodNames);
                            editText.setAdapter(adapter);
                        } catch (IllegalAccessException|NoSuchFieldException e) {
                            Log.e(Tag,"failed to find field "+Variables.get(variable));
                            editText.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.dropdownitem, generateFullAutoCompleteArray()));
                        }
                    }
                    else editText.setAdapter(new ArrayAdapter<>(getApplicationContext(),R.layout.dropdownitem,new String[0]));
                }
                else if(lastChar==' '||lastChar=='\n'){
                    editText.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.dropdownitem, Variables.keySet().toArray()));
                }
            }
        };
        editText.addTextChangedListener(watcher);
    }

    private String[] generateFullAutoCompleteArray() {
        Field[] fields = Methods.class.getFields();
        HashSet<String> all = new HashSet<>();
        for(Field field: fields) {
            try {
                all.addAll(((HashMap<String,String>) field.get(null)).keySet());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return all.toArray(new String[all.size()]);
    }

    private void checkForVariables(MultiAutoCompleteTextView editText,int start) {
        Editable text = editText.getText();
        int lineStart = start;
        while (text.charAt(lineStart) != '\n' && lineStart != 0) lineStart--;
        int lineEnd = start + 1;
        while (lineEnd < text.length() && text.charAt(lineEnd) != '\n') lineEnd++;
        String line = text.toString().substring(lineStart, lineEnd);
        List<String> parts = Arrays.asList(line.split("=|,"));
        while (parts.contains("")) {
            int index = parts.indexOf("");
            if (index < parts.size() - 1) {
                while (parts.get(index + 1).equals(""))
                    parts.remove(index + 1);
                parts.remove(index + 1);
            }
            parts.remove(index);
            if (index > 0) parts.remove(index - 1);
        }
        for (int i = 0; i < parts.size() - 1; i += 2) {
            List<String> temp = Arrays.asList(parts.get(i).split(" "));
            String key = temp.get(temp.size() - 1).trim();
            String part2 = parts.get(i + 1);
            if(part2!=null && !part2.equals("")) {
                String type = determineType(part2);
                Variables.put(key, type);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MethodTokenizer implements MultiAutoCompleteTextView.Tokenizer {

        public int findTokenStart(CharSequence text, int cursor) {
            int i = cursor;

            while (i > 0 && (text.charAt(i - 1) != '.'&&text.charAt(i - 1) != ' '&&text.charAt(i - 1) != '\n')) {
                i--;
            }
            while (i < cursor && (text.charAt(i) == '.'||text.charAt(i) == ' '||text.charAt(i) == '\n')) {
                i++;
            }

            return i;
        }

        public int findTokenEnd(CharSequence text, int cursor) {
            int i = cursor;
            int len = text.length();

            while (i < len) {
                if (text.charAt(i) == '.' || text.charAt(i) == ' '||text.charAt(i) == '\n') {
                    return i;
                } else {
                    i++;
                }
            }

            return len;
        }

        public CharSequence terminateToken(CharSequence text) {
            int i = text.length();

            while (i > 0 && (text.charAt(i - 1) == '.'||text.charAt(i - 1) == ' '||text.charAt(i - 1) == '\n')) {
                i--;
            }

            if (i > 0 && (text.charAt(i - 1) == '.'||text.charAt(i - 1) == ' '||text.charAt(i - 1) == '\n')) {
                return text;
            }
            else {
                if (text instanceof Spanned) {
                    SpannableString sp = new SpannableString(text);
                    TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                            Object.class, sp, 0);
                    return sp;
                } else {
                    return text;
                }
            }
        }
    }

    private String determineType(String expression)
    {
        expression = expression.replace(";","").trim();
        String type = "unknown";
        if(expression.equals(""))return type;
        Log.d(Tag,"Expression: "+expression);
        if (expression.contains("*")) {
            String[] temp = expression.split(Pattern.quote("*"));
            boolean onlyIntegers = true;
            for (String aTemp : temp) if (!determineType(aTemp).equals("Number")) onlyIntegers = false;
            if(onlyIntegers)type="Number";
        }
        else if (expression.contains("/")) {
            String[] temp = expression.split("/");
            boolean onlyIntegers = true;
            for (String aTemp : temp)
                if (!determineType(aTemp).equals("Number")) onlyIntegers = false;
            if(onlyIntegers)type="Number";
        }
        else if (expression.contains("-")) {
            String[] temp = expression.split("-");
            boolean onlyIntegers = true;
            for (String aTemp : temp) if (!determineType(aTemp).equals("Number")) onlyIntegers = false;
            if(onlyIntegers)type="Number";
        }
        else if(expression.contains("+")){
            String[] temp = expression.split(Pattern.quote("+"));
            boolean onlyIntegers = true;
            boolean containsString = false;
            for (String aTemp : temp) {
                String typePart = determineType(aTemp);
                if (!typePart.equals("Number")) onlyIntegers = false;
                if (typePart.equals("String")) containsString = true;
            }
            if(onlyIntegers)type="Number";
            if(containsString)type="String";
        }
        else if(expression.charAt(expression.length()-1)==')'){
            if(StringUtils.startsWithAny(expression,Methods.Window.keySet().toArray(new String[Methods.Window.size()]))){
                for(String s:Methods.Window.keySet()){
                    if(StringUtils.startsWith(expression,s)){
                        type = Methods.Window.get(s);
                    }
                }
            }
            else if(StringUtils.startsWithAny(expression,Variables.keySet().toArray(new String[Variables.size()]))){
                for(String s:Variables.keySet()){
                    if(StringUtils.startsWith(expression,s)){
                        String temp = expression.substring(expression.indexOf(".")+1);
                        if(temp.contains("("))
                            temp = temp.substring(0,temp.indexOf("("));
                        try {
                            Field field = Methods.class.getField(Variables.get(s));
                            type = ((HashMap<String,String>)field.get(null)).get(temp);
                            Log.d(Tag,"Extracted type:"+type);
                        } catch (NoSuchFieldException|IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else if (expression.indexOf("[") != expression.indexOf("]") && expression.contains("[") && expression.contains("]"))
            type = "Array";
        else if (expression.indexOf("\"") != expression.lastIndexOf("\"") || expression.indexOf("'") != expression.lastIndexOf("'"))
            type = "String";
        else if(expression.matches("[\\d,.]+"))
            type = "Number";
        else if(Variables.keySet().contains(expression))
            type = Variables.get(expression);
        return type;
    }

}
