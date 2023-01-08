package com.sogonggirls.gogogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sogonggirls.gogogo.storeInfo.StoreRecommend;
import com.sogonggirls.gogogo.survey.SurveyMeal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Roulette extends AppCompatActivity {
    private CircleManager circleManager;
    private RelativeLayout layoutRoulette;
    public KonfettiView konfettiView;

    private Button btnRotate;
    private TextView result;
    private int number;

    private ArrayList<String> STRINGS;
    private float initAngle = 0.0f;
    private int num_roulette;
    public static String result3;


    // 내 위치 확인
    private GpsTracker gpsTracker;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static double myLatitude = 0.0;   // 내 위치 위도
    public static double myLongitude = 0.0;   // 내 위치 경도


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);

        konfettiView = findViewById(R.id.viewKonfetti);
        number = SurveyMeal.MENU.size();
        result = findViewById(R.id.result);
        btnRotate = findViewById(R.id.btnRotate);
        layoutRoulette = findViewById(R.id.layoutRoulette);
        Button result2 = (Button) findViewById(R.id.result2);   // 내 주변 매장 확인하기


        num_roulette = number;
        STRINGS = SurveyMeal.MENU;
        circleManager = new CircleManager(Roulette.this, num_roulette);
        layoutRoulette.addView(circleManager);


        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateLayout(layoutRoulette, num_roulette);
            }
        });


        // 내 위치 확인하기
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        } else {
            checkRunTimePermission();
        }

        gpsTracker = new GpsTracker(Roulette.this);

        myLatitude = gpsTracker.getLatitude();
        myLongitude = gpsTracker.getLongitude();


        // 내 주변 매장 확인하기
        result2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result3 = result.getText().toString();
                if (result3.equals("result")) {
                    Toast.makeText(getApplicationContext(), "룰렛을 먼저 돌려주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), StoreRecommend.class);
                    startActivity(intent);
                }
            }
        });
    }

    // 뒤로가기 버튼 눌렀을때, 홈화면으로 이동하기
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Roulette.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    /* Rotate roulette */
    public void rotateLayout(final RelativeLayout layout, final int num) {
        final float fromAngle = getRandom(360) + 3600 + initAngle;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getResult(fromAngle, num); // start when animation complete
            }
        }, 3000);

        RotateAnimation rotateAnimation = new RotateAnimation(initAngle, fromAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_decelerate_interpolator));
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillEnabled(true);
        rotateAnimation.setFillAfter(true);
        layout.startAnimation(rotateAnimation);
    }

    /* Set numbers on roulette to random */
    public ArrayList<String> setRandom(int maxNumber, int num) {
        ArrayList<String> strings = new ArrayList<>();

        double r = Math.random();

        for (int i = 0; i < num; i++) {
            int rand = (int) (r * maxNumber);
            strings.add(String.valueOf(rand));
            r = Math.random();
        }

        return strings;
    }

    // get Angle to random
    private int getRandom(int maxNumber) {
        double r = Math.random();
        return (int)(r * maxNumber);
    }


    /* Choose one out of the 2-6 sections */
    private void getResult(float angle, int num_roulette) {
        String text = "";
        angle = angle % 360;

        Log.d("roulette", "getResult : " + angle);

        if (num_roulette == 2) {
            if (angle > 270 || angle <= 90) {
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 90 && angle <= 270) {
                text = STRINGS.get(0);
                buildAlert(text);
            }

        } else if (num_roulette == 3) {
            if (angle > 270 || angle <= 30) {
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 30 && angle <= 150) {
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 150 && angle <= 270) {
                text = STRINGS.get(0);
                buildAlert(text);
            }

        } else if (num_roulette == 4) {
            if (angle > 360 || angle <= 90) {
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 90 && angle <= 180) {
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 180 && angle <= 270) {
                text = STRINGS.get(0);
                buildAlert(text);
            } else if (angle > 270 && angle <= 360) {
                text = STRINGS.get(3);
                buildAlert(text);
            }

        } else if (num_roulette == 5) {
            if (angle > 342 || angle <= 54) { // 11   2
                text = STRINGS.get(3);
                buildAlert(text);
            } else if (angle > 54 && angle <= 126) { // 333   3
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 126 && angle <= 198) { // 222   4
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 198 && angle <= 270) { // 111    0
                text = STRINGS.get(0);
                buildAlert(text);
            } else if (angle > 270 && angle <= 342) { // 22     1
                text = STRINGS.get(4);
                buildAlert(text);
            }

        } else if (num_roulette == 6) {
            if (angle > 330 || angle <= 30) { // 22
                text = STRINGS.get(4);
                buildAlert(text);
            } else if (angle > 30 && angle <= 90) { // 11
                text = STRINGS.get(3);
                buildAlert(text);
            } else if (angle > 90 && angle <= 150) { // 333
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 150 && angle <= 210) { // 222
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 210 && angle <= 270) { // 111
                text = STRINGS.get(0);
                buildAlert(text);
            } else if (angle > 270 && angle <= 330) { // 3
                text = STRINGS.get(5);
                buildAlert(text);
            }
        }
        result.setText(text);
    }

    // if you want use AlertDialog then use this
    private void buildAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("메뉴 추천 결과!")
                .setMessage(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        layoutRoulette.setRotation(360 - initAngle);
                        konfettiView.build()
                                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                                .setDirection(0.0, 359.0)
                                .setSpeed(1f, 3f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(1500L)
                                .addShapes(Shape.RECT, Shape.CIRCLE)
                                .addSizes(new Size(11, 5))
                                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                                .streamFor(250, 5000L);
                    }

                });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public class CircleManager extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GRAY};
        private int num;

        public CircleManager(Context context, int num) {
            super(context);
            this.num = num;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int width = layoutRoulette.getWidth();
            int height = layoutRoulette.getHeight();
            int sweepAngle = 360 / num;

            RectF rectF = new RectF(0, 0, width, height);
            Rect rect = new Rect(0, 0, width, height);

            int centerX = (rect.left + rect.right) / 2;
            int centerY = (rect.top + rect.bottom) / 2;
            int radius = (rect.right - rect.left) / 2;

            int temp = 0;

            for (int i = 0; i < num; i++) {
                paint.setColor(COLORS[i]);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setAntiAlias(true);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawArc(rectF, temp, sweepAngle, true, paint);

                float medianAngle = (temp + (sweepAngle / 2f)) * (float) Math.PI / 180f;

                paint.setColor(Color.BLACK);
                paint.setTextSize(64);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);

                float arcCenterX = (float) (centerX + (radius * Math.cos(medianAngle))); // Arc's center X
                float arcCenterY = (float) (centerY + (radius * Math.sin(medianAngle))); // Arc's center Y

                // put text at middle of Arc's center point and Circle's center point
                float textX = (centerX + arcCenterX) / 2;
                float textY = (centerY + arcCenterY) / 2;

                canvas.drawText(STRINGS.get(i), textX, textY, paint);
                temp += sweepAngle;
            }
        }
    }


    // 내 위치 확인하기
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {
        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check = true;

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check = false;
                    break;
                }
            }

            if (check) {
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(Roulette.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(Roulette.this, "퍼미션이 거부되었습니다. 설정에서 퍼미션을 허용해야 합니다.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    void checkRunTimePermission() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(Roulette.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(Roulette.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {}
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Roulette.this, REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(Roulette.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(Roulette.this, REQUIRED_PERMISSIONS, PERMISSONS_REQUEST_CODE);
            }
            else {
                ActivityCompat.requestPermissions(Roulette.this, REQUIRED_PERMISSIONS, PERMISSONS_REQUEST_CODE);
            }
        }
    }

    public String getCurrentAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 7);
        } catch (IOException ioException) {
            Toast.makeText(this, "지오코더 서비스 사용 불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용 불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";
        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";
    }

    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Roulette.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n" + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                if (checkLocationServicesStatus()) {
                    Log.d("@@@", "onActivityResult : GPS 활성화 되어있음");
                    checkRunTimePermission();
                    return;
                }
                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}