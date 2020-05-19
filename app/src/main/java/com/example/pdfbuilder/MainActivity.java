package com.example.pdfbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.create_button);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPDF();
    }

    private void createPDF() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdfDocument myPdfdocument=new PdfDocument();
                Paint myPaint=new Paint();

                PdfDocument.PageInfo myPageInfo1=new PdfDocument.PageInfo.Builder(250,400,1).create();
                PdfDocument.Page myPage1=myPdfdocument.startPage(myPageInfo1);

                Canvas canvas=myPage1.getCanvas();
                canvas.drawText("First PDF making Trial",40,50,myPaint);
                myPdfdocument.finishPage(myPage1);

                File file=new File(Environment.getExternalStorageDirectory(),"/FirstPDF.pdf");
                try {
                    myPdfdocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myPdfdocument.close();

            }
        });
    }
}
