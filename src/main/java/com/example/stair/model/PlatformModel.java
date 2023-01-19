package com.example.stair.model;

import com.example.stair.Platform.ETPlatform;
import com.example.stair.Platform.NPUPlatform;
import com.example.stair.StartController;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class PlatformModel {
    public Map<Integer, Integer> lengthStepsX = new HashMap<>();

    public Map<Integer, Integer> lengthStepsY = new HashMap<>();

    public double angle;

    public int angleET;

    public double angleNPU;

    private int stepNumY;

    private int firstPoint;

    private int countMatchingPoints = 0;

    public int lengthWayET;

    public int lengthWayNPU;

    public int lengthWayOnLowerPlatsET;

    public int lengthWayOnLowerPlatsNPU;

    public int lengthClearanceRampET;

    public int lengthClearanceRampNPU;

    private int clearanceMax = 0;

    private int countClearanceMin;

    private int countClearanceMax;

    private int clearanceMin = 0;

    private int maxClearanceGOST = 200;

    private void addLengthStep(Map<Integer, Integer> stepSize, Map<Integer, Integer> lengthStep) {
        int size = stepSize.get(1);
        for (int i = 1; i <= stepSize.size(); i++) {
            if (stepSize.get(i) != 0)
                lengthStep.put(i, size);
            else break;
            if (i != 30)
                size += stepSize.get(i + 1);
        }
    }

    private double searchAngle(Map<Integer, Integer> lengthStepsX, Map<Integer, Integer> lengthStepsY) {
        double angle = 90;
        if (lengthStepsY.size() == 1)
            return 30;
        else {
            for (int i = 1; i < lengthStepsY.size(); i++) {
                double x;
                if (i == 1)
                    x = lengthStepsX.get(lengthStepsX.size());
                else {
                    x = lengthStepsX.get(lengthStepsX.size()) - lengthStepsX.get(i - 1);
                }
                double y = lengthStepsY.get(lengthStepsY.size()) - lengthStepsY.get(i);
                double angleCurrent = Math.toDegrees(Math.atan(y / x));
                if (angle == angleCurrent)
                    countMatchingPoints++;
                if (angle >= angleCurrent) {
                    angle = angleCurrent;
                    stepNumY = i;
                }
            }
        }
        return angle;
    }

    private int findFirstPoint(Map<Integer, Integer> lengthStepsX, Map<Integer, Integer> lengthStepsY, int stepNumY, double angle) {
        int x0;
        if (lengthStepsY.size() > 1) {
            int y0 = 0;
            int xe = lengthStepsX.get(lengthStepsX.size());
            int ye = lengthStepsY.get(lengthStepsY.size());
            int ym = lengthStepsY.get(stepNumY);
            int xm;
            if (stepNumY <= 1)
                xm = 0;
            else
                xm = lengthStepsX.get(stepNumY - 1);

            x0 = (((y0 - ym) * (xe - xm)) / (ye - ym)) + xm;
        } else {
            x0 = (int) (lengthStepsY.get(1) / Math.tan(Math.toRadians(angle)));
        }
        return Math.abs(x0);
    }

    private int lengthWay(double angle) {
        int ye = lengthStepsY.get(lengthStepsY.size());
        int xe = (int) Math.round(ye / Math.tan(Math.toRadians(angle)));
        return (int) Math.sqrt(Math.pow(xe, 2) + Math.pow(ye, 2));
    }

    public int lengthStair() {
        int l = 0;
        int x;
        int y;
        if (lengthStepsY.size() > 1) {
            x = lengthStepsX.get(lengthStepsX.size());
            y = lengthStepsY.get(lengthStepsY.size()) - lengthStepsY.get(1);
            l = (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
        return l;
    }

    private int lengthWayOnLowerPlats(int clearanceOnStep, double angle, int stepNumY) {
        int y;
        int l;
        if (stepNumY > 1) {
            y = lengthStepsY.get(stepNumY) + clearanceOnStep;
        } else {
            y = lengthStepsY.get(1) + clearanceOnStep;
        }
        int xl = (int) Math.round(y / Math.tan(Math.toRadians(angle)));
        if (stepNumY > 1) {
            l = xl - lengthStepsX.get(stepNumY - 1);
        } else {
            l = xl;
        }
        return l;
    }

    private int lengthClearanceRamp(int lengthWayOnLowerPlats, double angle) {
        int l;
        int xStair;
        if (lengthStepsX.size() >= 1)
            xStair = lengthStepsX.get(lengthStepsX.size());
        else xStair = 0;
        int xWay;
        if (lengthStepsY.size() > 1) {
            xWay = (int) ((lengthStepsY.get(lengthStepsY.size())) / Math.tan(Math.toRadians(angle))) - lengthWayOnLowerPlats;
        } else {
            xWay = 0;
        }
        if (lengthStepsY.size() > 1) {
            l = xStair - xWay;
        } else {
            l = lengthWayOnLowerPlats - firstPoint;
        }
        return l;
    }

    private void findNumberMinClearanceStep(double angle, int lengthWayOnLowerPlats) {
        clearanceMax = 0;
        countClearanceMin = 0;
        countClearanceMax = 0;
        clearanceMin = maxClearanceGOST;

        int clearance;
        int x;
        int y;
        int stairY;
        for (int i = 1; i < lengthStepsY.size(); i++) {
            if (i == 1) {
                x = lengthWayOnLowerPlats;
            } else {
                x = lengthWayOnLowerPlats + lengthStepsX.get(i - 1);
            }
            y = (int) (Math.tan(Math.toRadians(angle)) * x);
            stairY = lengthStepsY.get(i);
            clearance = y - stairY;
            if (clearance > clearanceMax) {
                clearanceMax = clearance;
                countClearanceMax = i;
            }
            if (clearance < clearanceMin) {
                clearanceMin = clearance;
                countClearanceMin = i;
            }
        }
    }

    public void startModel() {
        lengthStepsY.clear();
        lengthStepsX.clear();
        addLengthStep(StartController.stepHeights, lengthStepsY);
        addLengthStep(StartController.stepLengths, lengthStepsX);
        angle = searchAngle(lengthStepsX, lengthStepsY);
        angleET = (int) angle;
        angleNPU = angle;
        firstPoint = findFirstPoint(lengthStepsX, lengthStepsY, stepNumY, angle);


        boolean checkNPU = false;
        int countNPU = 0;
        int countNPU1 = 0;
        do {
            lengthWayNPU = lengthWay(angleNPU);
            if (countNPU1 == 0) {
                lengthWayOnLowerPlatsNPU = lengthWayOnLowerPlats(NPUPlatform.clearanceOnStep, angleNPU, stepNumY);
            } else {
                lengthWayOnLowerPlatsNPU = lengthWayOnLowerPlats(NPUPlatform.clearanceOnStep, angleNPU, countClearanceMin);
            }
            lengthClearanceRampNPU = lengthClearanceRamp(lengthWayOnLowerPlatsNPU, angleNPU);

            if(clearanceMax < maxClearanceGOST && clearanceMin > NPUPlatform.clearanceOnStep - 1 && lengthClearanceRampNPU < 10){
                for(int i = 1; i <= maxClearanceGOST - NPUPlatform.clearanceOnStep; i++) {
                    lengthWayOnLowerPlatsNPU = lengthWayOnLowerPlats(NPUPlatform.clearanceOnStep + i, angleNPU, countClearanceMin);
                    lengthClearanceRampNPU = lengthClearanceRamp(lengthWayOnLowerPlatsNPU, angleNPU);
                    if(lengthClearanceRampNPU > 10){
                        break;
                    }
                }
            }
            findNumberMinClearanceStep(angleNPU, lengthWayOnLowerPlatsNPU);

            if (lengthClearanceRampNPU >= (NPUPlatform.lengthRamp - NPUPlatform.overlapRamp) && angleNPU > 0) {
                checkNPU = true;
                if (countNPU == 0) {
                    angleNPU = (int) angle;
                    countNPU++;
                } else {
                    angleNPU--;
                    countNPU++;
                }
            } else if ((lengthClearanceRampNPU >= (NPUPlatform.lengthRamp - NPUPlatform.overlapRamp) && angleNPU <= 0) ||
                    (lengthClearanceRampNPU < 0 && angleNPU <= 0)) {
                angleNPU = 0;
                lengthWayNPU = 0;
                lengthWayOnLowerPlatsNPU = 0;
                lengthClearanceRampNPU = 0;
                break;
            } else if (clearanceMin <= NPUPlatform.clearanceOnStep - 1 && countNPU1 == 0) {
                checkNPU = true;
            } else
                break;
            countNPU1++;
        }
        while (checkNPU);

        if(clearanceMax > maxClearanceGOST) {
            angleNPU = 0;
            lengthWayNPU = 0;
            lengthWayOnLowerPlatsNPU = 0;
            lengthClearanceRampNPU = 0;
        }

        System.out.println("номер мин " + countClearanceMin);
        System.out.println("мин " + clearanceMin);
        System.out.println("номер макс " + countClearanceMax);
        System.out.println("макс " + clearanceMax);

        boolean checkET = false;
        int countET = 0;
        do {
            lengthWayET = lengthWay(angleET);

            if (countET == 0) {
                lengthWayOnLowerPlatsET = lengthWayOnLowerPlats(ETPlatform.clearanceOnStep, angleET, stepNumY);
            } else {
                lengthWayOnLowerPlatsET = lengthWayOnLowerPlats(ETPlatform.clearanceOnStep, angleET, countClearanceMin);
            }
            lengthClearanceRampET = lengthClearanceRamp(lengthWayOnLowerPlatsET, angleET);

            if(clearanceMax < maxClearanceGOST && clearanceMin > ETPlatform.clearanceOnStep - 1 && lengthClearanceRampET < 10){
                for(int i = 1; i <= maxClearanceGOST - ETPlatform.clearanceOnStep; i++) {
                    lengthWayOnLowerPlatsET = lengthWayOnLowerPlats(ETPlatform.clearanceOnStep + i, angleET, countClearanceMin);
                    lengthClearanceRampET = lengthClearanceRamp(lengthWayOnLowerPlatsET, angleET);
                    if(lengthClearanceRampET > 10){
                        break;
                    }
                }
            }

            findNumberMinClearanceStep(angleET, lengthWayOnLowerPlatsET);

            if (lengthClearanceRampET >= (ETPlatform.lengthRamp - ETPlatform.overlapRamp) && angleET > 0) {
                angleET--;
                checkET = true;
            } else if ((lengthClearanceRampET >= (ETPlatform.lengthRamp - ETPlatform.overlapRamp) && angleET < 1) || (lengthClearanceRampET < 0 && angleET < 1)) {
                angleET = 0;
                lengthWayET = 0;
                lengthWayOnLowerPlatsET = 0;
                lengthClearanceRampET = 0;
                break;

            } else if (clearanceMin <= ETPlatform.clearanceOnStep - 1 && countET == 0) {
                checkET = true;
            } else
                break;
            countET++;

        }
        while (checkET);
        if(clearanceMax > maxClearanceGOST) {
            angleET = 0;
            lengthWayET = 0;
            lengthWayOnLowerPlatsET = 0;
            lengthClearanceRampET = 0;
        }




        System.out.println("stair number step: " + stepNumY);
        System.out.println("count matching points: " + countMatchingPoints);
        System.out.println("coordinate height step by Y: " + lengthStepsY);
        System.out.println("coordinate length step by Y: " + lengthStepsX);
        System.out.println("length way on lower plats by X: " + firstPoint);
        System.out.println("length way on lower plats ET: " + lengthWayOnLowerPlatsET);
        System.out.println("length way on lower plats NPU: " + lengthWayOnLowerPlatsNPU);
        System.out.println("length clearance ramp ET: " + lengthClearanceRampET);
        System.out.println("length clearance ramp NPU: " + lengthClearanceRampNPU);
        System.out.println("номер мин " + countClearanceMin);
        System.out.println("мин " + clearanceMin);
        System.out.println("номер макс " + countClearanceMax);
        System.out.println("макс " + clearanceMax);

    }
}
