package com.JIAT.Tumble;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/6/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class AnswerPostLayout extends PostLayout{

    protected LinearLayout questionLayout;
    protected LinearLayout answerLayout;

    protected TextView question;
    protected TextView asker;
    protected TextView answer;

    public AnswerPostLayout(Context context, Post post)
    {
        super(context, post);

        testView.setText(""); //TODO: Remove the need for this

        questionLayout = new LinearLayout(context);
        questionLayout.setOrientation(LinearLayout.VERTICAL);

        answerLayout = new LinearLayout(context);

        question = new TextView(context);
        question.setTextColor(Color.BLACK);
        question.setText(post.getQuestion());

        asker = new TextView(context);
        asker.setTextColor(Color.BLACK);
        String askName = post.getAsking_name();
        //If the asker is not anon, we'll setup a special link to the name
        if(!askName.equals("anonymous"))
        {
            //TODO: Create Activity link to asker's profile
        }
        asker.setText(askName + " Asked:");

        //Give the question layout a bit of a background to it
        RoundRectShape roundedRect = new RoundRectShape(new float[] {10, 10, 10, 10, 10, 10, 10 ,10}, null, null);
        ShapeDrawable drawableShape = new ShapeDrawable(roundedRect);
        drawableShape.getPaint().setColor(Color.parseColor("#D3D3D3"));

        questionLayout.setPadding(5,5,5,5);
        questionLayout.setBackground(drawableShape);



        answer = new TextView(context);
        answer.setTextColor(Color.BLACK);
        answer.setText(Html.fromHtml(post.getAnswer()));
        answer.setPadding(0, 20, 0, 0);
        answer.setTextSize(16);

        questionLayout.addView(asker);
        questionLayout.addView(question);

        answerLayout.addView(answer);

        mainLayout.addView(questionLayout);
        mainLayout.addView(answerLayout);
    }


}