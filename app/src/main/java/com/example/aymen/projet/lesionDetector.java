package com.example.aymen.projet;

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by aymen on 30/04/2017.
 */
public class lesionDetector {
    private static final String TAG = "lesion";
    private List<Mat> HSLlsit;
    private double M00;
    private double M01;
    private double M10;
    private Mat MSegm;
    private Mat Mclose;
    private Mat Mthreshold;
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private List<MatOfPoint> contours;
    private Mat kernel;
    private Mat mGray;
    private Mat mHSL;
    private Mat mHierarchy;
    private Mat mRgba;
    private int maxAreaIdx;
    private Moments moments;
    private double mu02;
    private double mu11;
    private double mu20;
    private double posX;
    private double posY;
    private Mat resizeM;
    private Mat shape1;
    private Mat shape2;
    private double A,SX,SY,KX,KY,PI,CI,d;
    double p;
    double []tab;
    public lesionDetector() {

        CreationVariable();
    }
    public void CreationVariable()
    {
        this.mGray = new Mat();
        this.mHSL = new Mat();
        this.resizeM = new Mat();
        this.mHierarchy = new Mat();
        this.HSLlsit = new ArrayList();
        this.MSegm = new Mat();
        this.Mthreshold = new Mat();
        this.Mclose = new Mat();
        this.moments = new Moments();
        this.shape1 = new Mat();
        this.shape2 = new Mat();
    }
    public void Segment(Mat mat)
    {
        Log.i("lesion", "start IP 1");
        this.mRgba = mat;
        this.resizeM = new Mat(this.mRgba.width(), this.mRgba.height(), this.mRgba.type());
        Imgproc.resize(this.mRgba, this.resizeM, this.resizeM.size(), 0.0D, 0.0D, 2);
        this.mRgba.copyTo(this.shape1);
        this.mRgba.copyTo(this.shape2);
        Imgproc.cvtColor(this.resizeM, this.resizeM, 1);
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(10.0D, 10.0D));
        Imgproc.medianBlur(this.resizeM, this.resizeM, 5);
        Imgproc.pyrMeanShiftFiltering(this.resizeM, this.MSegm, 20.0D, 30.0D);
        Imgproc.cvtColor(this.MSegm, this.mHSL, 3);
        Core.split(this.mHSL, this.HSLlsit);
        this.HSLlsit.get(1).convertTo(this.mGray, CvType.CV_8UC1);
        Imgproc.threshold(this.mGray, this.Mthreshold, 0.0D, 255.0D, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);
       Imgproc.dilate(Mthreshold, Mclose, kernel);
       Imgproc.erode(Mthreshold,Mclose,kernel);
        Core.bitwise_not(this.Mclose, this.Mclose);
        this.contours = new ArrayList<MatOfPoint>();
       Imgproc.findContours(this.Mclose, this.contours, this.mHierarchy, 1, 1);
        Log.i("lesion", "start IP 2");
        CalculABCD();
    }
    public void CalculABCD()
    {
            Log.i("lesion", "start IP 3");
            this.moments = Imgproc.moments(this.contours.get(this.maxAreaIdx));
            this.M00 = this.moments.get_m00();
            this.M10 = this.moments.get_m10();
            this.M01 = this.moments.get_m01();
            this.posX = (int) (this.M10 / this.M00);
            this.posY = (int) (this.M01 / this.M00);
            this.mu20 = this.moments.get_mu20();
            this.mu02 = this.moments.get_mu02();
            this.mu11 = this.moments.get_mu11();
            double m00=this.moments.get_m00();
            p=moments.get_mu30();
            double N=this.mu20+this.mu02-(Math.sqrt(Math.pow(this.mu20-mu02,2)+4*(Math.pow(mu11,2))))/2;
            Log.d(TAG, "valeur1=" +N);
            double N1=this.mu20+this.mu02+(Math.sqrt(Math.pow(this.mu20-mu02,2)+4*(Math.pow(mu11,2))))/2;
            Log.d(TAG, "valeur2=" +N1);
             A=N/N1;
            Log.d(TAG, "Allongment=" +A);
             SX=moments.get_mu30()/Math.pow(mu20, 3 / 2);
            Log.d(TAG, "SegmentationX=" +SX);
             SY=moments.get_mu03()/Math.pow(mu02, 3 / 2);
            Log.d(TAG, "SegmentationY=" +SY);
             KX=(Math.pow(mu20,4)/Math.pow(mu20,2))-3;
            Log.d(TAG, "KurtosisX="+ KX);
             KY=(Math.pow(mu02,4)/Math.pow(mu02,2))-3;
            Log.d(TAG, "KurtosisY=" +KY);
             PI = Imgproc.arcLength(new MatOfPoint2f(((MatOfPoint) this.contours.get(this.maxAreaIdx)).toArray()), true);
             Log.d(TAG, "périmètre=" +PI);
             CI=Math.pow(PI,2)/(4*3.141592653589793D*m00);
            Log.d(TAG, "l’indice de compacité=" +CI);
           this.tab=new double[]{A,SX,SY,KX,KY,CI,0};
            MatOfPoint2f localMatOfPoint2f = new MatOfPoint2f(((MatOfPoint)this.contours.get(this.maxAreaIdx)).toArray());
            float[] arrayOfFloat = new float[1];
            Imgproc.minEnclosingCircle(localMatOfPoint2f, new Point(), arrayOfFloat);
             d= 2.0F * arrayOfFloat[0];
             Log.i(TAG, "Diameter=" + d);

    }
    public  double[] getvals()
    {

        return this.tab;
    }


    private int[] calculHist(Mat src)
    {
        int[] Sym_Occur = new int[255];
        Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
        Log.d("azerty","n1");
        List<Mat>arrMat= Arrays.asList(src);
        Log.d("azerty","n2");
        MatOfInt histSize = new MatOfInt(256);
        Log.d("azerty","n3");
        final MatOfFloat histRange = new MatOfFloat(0f, 256f);
        Log.d("azerty","n4");
        MatOfInt channels = new MatOfInt(3);
        Log.d("azerty","n5");
        boolean accumulate = false;
        Log.d("azerty","n6");
        Mat hist = new Mat();
        Log.d("azerty","n7");
        Imgproc.calcHist(arrMat, channels, new Mat(), hist, histSize, histRange, accumulate);
        Log.d("azerty", "n8");
        int [] x = new int[0];



        for(int i=0;i<255;i++) {

             Sym_Occur [i]= hist.get(i,0,x);
        }
    return Sym_Occur;
    }

    public double Entropy(int [] t)
    {
        double E=0;
        double[] res = new double[255];


        for(int i=0;i<255;i++)
        {
            res[i]= t[i]/150;
            if (res[i]==0) {
                res[i] = 1;
            }
        }
        for (int j=0; j<255;j++)
        {

            E+= res[j]+Math.log(res[j]);
        }

        return (E);
    }
    public void DrawContour(Mat paramMat) {
        Imgproc.drawContours(paramMat, this.contours, this.maxAreaIdx, new Scalar(255, 255, 255,255),2);
    }
    public void FilltrageImage(Mat paramMat)
    {
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(10.0D, 10.0D));
        Imgproc.medianBlur(paramMat, paramMat, 5);
        Imgproc.morphologyEx(paramMat, paramMat, Imgproc.MORPH_CLOSE, kernel);
    }
    public Mat getMat(Mat paramMat) {
        Rect localRect = Imgproc.boundingRect(contours.get(maxAreaIdx));
        this.x1 = (int) Math.max(localRect.tl().x - 30, 2.0D);
        this.y1 = (int) Math.max(localRect.tl().y - 30, 2.0D);
        this.x2 = (int) Math.min(localRect.br().x + 30, -2 + paramMat.width());
        this.y2 = (int) Math.min(localRect.br().y + 30, -2 + paramMat.height());
        Mat localMat1 = paramMat.submat(new Rect(new Point(this.x1, this.y1), new Point(this.x2, this.y2))).clone();
        Log.i("lesion", "ok croped 1 ....");
        Mat localMat2 = new Mat(150, 150, this.mRgba.type());
        Log.i("lesion", "ok croped 2 ....");
        Imgproc.resize(localMat1, localMat2, localMat2.size(), 0.0D, 0.0D, 2);
        Log.i("lesion", "ok croped 3 ....");
        return localMat2;
    }

    public Mat getRGB() {

        return this.mRgba;
    }
    public Mat getThresholdMat() {

        return this.Mthreshold;
    }
    public Mat getMclose()
    {
        DrawContour(Mclose);
        FilltrageImage(Mclose);
        return this.Mclose;
    }
    public Mat getshape2() {
        DrawContour(this.shape2);
        FilltrageImage(this.shape2);
        return this.shape2;
    }

}
