package com.example.donutClick;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.random;

public class donutClick extends Application {
    // Creating needed panes...
    Pane pane = new StackPane();
    VBox start = new VBox();
    VBox versionBox = new VBox();
    VBox scoreBox = new VBox();
    VBox remainingBox = new VBox();
    HBox restartMenu = new HBox();
    Scene scene = new Scene(pane, 1000, 900);


    // Backgrounds and images...
    Image img1 = new Image(donutClick.class.getResource("play.png").toString());
    Image img2 = new Image(donutClick.class.getResource("Quit.png").toString());
    Image img3 = new Image(donutClick.class.getResource("tryAgain.png").toString());
    Image img4 = new Image(donutClick.class.getResource("logo.png").toString());
    Image img5 = new Image(donutClick.class.getResource("donut-black.png").toString());
    Image img6 = new Image(donutClick.class.getResource("donut-bub.png").toString());
    Image img7 = new Image(donutClick.class.getResource("donut-red.png").toString());
    Image img8 = new Image(donutClick.class.getResource("donut-yellow.png").toString());
    Image img9 = new Image(donutClick.class.getResource("donut-gold.png").toString());
    Image img10 = new Image(donutClick.class.getResource("Rules.png").toString());
    Image img11 = new Image(donutClick.class.getResource("bg.jpg").toString());


    ImageView startButtonBackground = new ImageView(img1);
    ImageView quitButtonBackground = new ImageView(img2);
    ImageView restartButtonBackground = new ImageView(img3);

    ImageView title = new ImageView(img4);
    ImageView body = new ImageView(img5);
    ImageView body1 = new ImageView(img6);
    ImageView body2 = new ImageView(img7);
    ImageView body3 = new ImageView(img8);
    ImageView body4 = new ImageView(img9);
    ImageView rulesScreen = new ImageView(img10);

    ImageView backGround1 = new ImageView(img11);
    int score = 0, remaining = 30;


    // Texts and buttons...
    Text version = new Text(5, 10, "V 1.02");
    Text scoreText = new Text(5, 10, "Score: " + score);
    Text remainingText = new Text(5, 10, "Remaining: " + remaining);
    Text top = new Text(225, 100, "Top 5: ");
    Button startButton = new Button("",startButtonBackground);

    Button quitButton = new Button("", quitButtonBackground);
    Button restartButton = new Button("", restartButtonBackground);

    // the height and the width of the buttons as a global variables...
    int width = 300, height = 105;
    Donut donut = new Donut();

    ArrayList<Integer> ranks = new ArrayList<>();

    int[] Top5 = new int[5];

    double y = 25, y1 = 25, y2 = 25, y3 = 25, y4 = 25;

    @Override
    public void start(Stage stage) {
        // setting the background image
        backGround1.setFitHeight(900);
        backGround1.setFitWidth(1000);
        pane.getChildren().add(backGround1);


        // then designing the game title and aligned it in the middle
        title.setFitHeight(130);
        title.setFitWidth(750);
        restartMenu.setSpacing(4.5);


        // the version designing
        version.setFill(Color.WHITE);
        version.setStyle("-fx-font: 15 arial;");
        version.setVisible(true);

        scoreText.setFill(Color.WHITE);
        scoreText.setStyle("-fx-font: 25 arial;");
        scoreText.setVisible(true);

        remainingText.setFill(Color.WHITE);
        remainingText.setStyle("-fx-font: 25 arial;");
        remainingText.setVisible(true);

        remainingBox.getChildren().add(remainingText);
        remainingBox.setAlignment(Pos.BASELINE_RIGHT);


        scoreBox.getChildren().add(scoreText);
        scoreBox.setAlignment(Pos.BASELINE_LEFT);

        // adding the version into the pane to make it visible
        versionBox.getChildren().add(version);
        versionBox.setAlignment(Pos.BOTTOM_LEFT);


        restartButton.setOnAction(e -> restarting(getVbox(Top5)));
        restartButton.setOpacity(0.5);
        restartButton.setAlignment(Pos.CENTER_LEFT);
        restartButton.setOnMouseExited(e -> buttonHoveringOut(restartButton));
        restartButton.setOnMouseEntered(e -> buttonHoveringIn(restartButton));


        restartMenu.setVisible(false);
        restartMenu.setAlignment(Pos.CENTER);

        restartButtonBackground.setFitWidth(width - 50);
        restartButtonBackground.setFitHeight(height - 15);
        restartMenu.getChildren().add(restartButton);

        // adding all the element to the start pane and then make it dimmer


        // adding everything to the main pane
        pane.getChildren().add(versionBox);
        start.getChildren().addAll(title, startButton, quitButton);
        Start();

        pane.getChildren().add(start);


        stage.setTitle("Donut Click");
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }

    public void Start() {
        title.setVisible(true);

        // adding both buttons to the needed width and height
        startButtonBackground.setFitWidth(width);
        startButtonBackground.setFitHeight(height);
        //.................................
        quitButtonBackground.setFitWidth(width - 50);
        quitButtonBackground.setFitHeight(height - 15);

        // setting the START button to be more active to the mouse and what to do when clicked
        startButton.setOnAction(e -> rules());
        startButton.setOpacity(0.5);
        startButton.setVisible(true);
        startButton.setAlignment(Pos.CENTER);
        startButton.setOnMouseExited(e -> buttonHoveringOut(startButton));
        startButton.setOnMouseEntered(e -> buttonHoveringIn(startButton));


        // setting the QUIT button to be more active to the mouse and what to do when clicked
        quitButton.setOnAction(e -> Platform.exit());
        quitButton.setOpacity(0.5);
        quitButton.setVisible(true);
        quitButton.setAlignment(Pos.CENTER);
        quitButton.setOnMouseExited(e -> buttonHoveringOut(quitButton));
        quitButton.setOnMouseEntered(e -> buttonHoveringIn(quitButton));


        start.setSpacing(55);
        start.setVisible(true);
        start.setOpacity(0.5);
        start.setAlignment(Pos.CENTER);


    }

    public void buttonHoveringOut(Button button) {
        // this function will make the button smaller and dimmer if the mouse did not enter its area.
        button.setOpacity(0.5);

        startButtonBackground.setFitHeight(height);
        startButtonBackground.setFitWidth(width);
    }


    public void buttonHoveringIn(Button button) {
        // this function will make the button bigger and lighter if the mouse did enter its area.
        button.setOpacity(1.5);

        startButtonBackground.setFitHeight(height + 10);
        startButtonBackground.setFitWidth(width + 20);
    }


    private VBox getVbox(int[] top5) {
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        Label Top5 = new Label("TOP 5");
        Top5.setTextFill(Color.WHITE);
        Top5.setStyle("-fx-font: 50 arial;");
        vBox.getChildren().add(Top5);
        int order = 1;
        for (int element : top5) {
            Label label = new Label(order + "- " + element);
            label.setTextFill(Color.WHITE);
            label.setStyle("-fx-font: 25 arial;");
            vBox.getChildren().add(label);
            order++;

        }
        if (!restartMenu.getChildren().contains(quitButton)) {
            restartMenu.getChildren().add(quitButton);
        }

        restartButton.setOnAction(e -> restarting(vBox));
        vBox.getChildren().addAll(restartMenu);
        restartMenu.setVisible(true);
        vBox.setVisible(true);
        vBox.setSpacing(45);
        return vBox;
    }


    private int[] setTop5(int[] top5) {

        for (int i = 0; i < 5; i++) {
            try {
                top5[i] = ranks.get(i);
            } catch (Exception e) {
                top5[i] = 0;
            }
        }
        return top5;
    }


    public static void main(String[] args) {
        launch();
    }


    public class Donut extends Pane {
        // crating the animation of the donut sliding down
        private Timeline animation, animation1, animation2, animation3, animation4;
        private int count = 0;
        private int count1 = 0;
        private int count2 = 0;
        private int count3 = 0;
        private int count4 = 0;
        private int totalCount = 0;

        public Donut() {
            // this constructor will set the donut to the start position then add it to the pane
            body.setFitWidth(75);
            body.setFitHeight(75);
            body.setViewOrder(0);
            body.setX(random() * 850);
            body.setY(25);
            body.setVisible(true);

            body1.setFitWidth(65);
            body1.setFitHeight(65);
            body1.setViewOrder(0);
            body1.setX(random() * 850);
            body1.setY(25);
            body1.setVisible(true);

            body2.setFitWidth(55);
            body2.setFitHeight(55);
            body2.setViewOrder(0);
            body2.setX(random() * 850);
            body2.setY(25);
            body2.setVisible(true);

            body3.setFitWidth(45);
            body3.setFitHeight(45);
            body3.setViewOrder(0);
            body3.setX(random() * 850);
            body3.setY(25);
            body3.setVisible(true);

            body4.setFitWidth(35);
            body4.setFitHeight(35);
            body4.setViewOrder(0);
            body4.setX(random() * 850);
            body4.setY(25);
            body4.setVisible(true);


            getChildren().addAll(body, body1, body2, body3, body4);

        }


        public void pause() {
            animation4.pause();
            animation3.pause();
            animation2.pause();
            animation1.pause();
            animation.pause();
        }


        public void play() {
            scoreText.setText("Score: " + score);
            remainingText.setText("Remaining: " + (remaining));
            scoreBox.setVisible(true);
            remainingBox.setVisible(true);

            body.setVisible(true);
            body1.setVisible(true);
            body2.setVisible(true);
            body3.setVisible(true);
            body4.setVisible(true);


            count = 0;
            count1 = 0;
            count2 = 0;
            count3 = 0;
            count4 = 0;
            totalCount = 0;

            animation = new Timeline(
                    new KeyFrame(Duration.millis(18), e -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();


            animation1 = new Timeline(
                    new KeyFrame(Duration.millis(16), e -> moveBall1()));
            animation1.setCycleCount(Timeline.INDEFINITE);
            animation1.play();

            animation2 = new Timeline(
                    new KeyFrame(Duration.millis(14), e -> moveBall2()));
            animation2.setCycleCount(Timeline.INDEFINITE);
            animation2.play();

            animation3 = new Timeline(
                    new KeyFrame(Duration.millis(12), e -> moveBall3()));
            animation3.setCycleCount(Timeline.INDEFINITE);
            animation3.play();

            animation4 = new Timeline(
                    new KeyFrame(Duration.millis(10), e -> moveBall4()));
            animation4.setCycleCount(Timeline.INDEFINITE);
            animation4.play();

        }

        public void increaseSpeed() {
            // this increase is a bit slow just to make the game more fun to play
            animation.setRate(animation.getRate() + 0.6);
        }

        public void increaseSpeed1() {
            // this increase is a bit slow just to make the game more fun to play
            animation1.setRate(animation1.getRate() + 0.6);
        }

        public void increaseSpeed2() {
            // this increase is a bit slow just to make the game more fun to play
            animation2.setRate(animation2.getRate() + 0.6);
        }

        public void increaseSpeed3() {
            // this increase is a bit slow just to make the game more fun to play
            animation3.setRate(animation3.getRate() + 0.6);
        }

        public void increaseSpeed4() {
            // this increase is a bit slow just to make the game more fun to play
            animation4.setRate(animation4.getRate() + 0.6);
        }


        private void moveBall() {


            body.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    y = 25;
                    count++;
                    totalCount++;
                    score++;
                    scoreText.setText("Score: " + score);
                    remainingText.setText("Remaining: " + (remaining - totalCount));
                    body.setX(random() * 850);
                    body.setY(25);
                    increaseSpeed();
                }

            });

            if (count == 6) {
                animation.pause();
                body.setVisible(false);
            }

            if (body.getY() > 820 || totalCount == 30) /* if the donut touched the border */ {
                // set the Y to the start
                body.setVisible(false);
                body1.setVisible(false);
                body2.setVisible(false);
                body3.setVisible(false);
                body4.setVisible(false);
                scoreBox.setVisible(false);
                remainingBox.setVisible(false);
                ranks.add(score);
                Collections.sort(ranks, Collections.reverseOrder());
                setTop5(Top5);
                pane.getChildren().add(getVbox(Top5));
                score = 0;
                pause();

            }
            // increasing the Y means moving downward
            body.setY(y++);


        }


        private void moveBall1() {
            body1.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    score += 2;
                    count1++;
                    totalCount++;
                    scoreText.setText("Score: " + score);
                    remainingText.setText("Remaining: " + (remaining - totalCount));
                    y1 = 25;
                    body1.setX(random() * 850);
                    body1.setY(25);
                    increaseSpeed1();

                }

            });

            if (count1 == 6) {
                animation1.pause();
                body1.setVisible(false);
            }

            if (body1.getY() > 830 || totalCount == 30) /* if the donut touched the border */ {
                // set the Y to the start
                body.setVisible(false);
                body1.setVisible(false);
                body2.setVisible(false);
                body3.setVisible(false);
                body4.setVisible(false);
                scoreBox.setVisible(false);
                remainingBox.setVisible(false);
                ranks.add(score);
                Collections.sort(ranks, Collections.reverseOrder());
                setTop5(Top5);
                pane.getChildren().add(getVbox(Top5));
                score = 0;
                pause();
            }
            // increasing the Y means moving downward
            body1.setY(y1++);

        }

        private void moveBall2() {


            body2.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    score += 3;
                    count2++;
                    totalCount++;
                    y2 = 25;
                    scoreText.setText("Score: " + score);
                    remainingText.setText("Remaining: " + (remaining - totalCount));
                    body2.setX(random() * 850);
                    body2.setY(25);
                    increaseSpeed2();

                }

            });

            if (count2 == 6) {
                animation2.pause();
                body2.setVisible(false);
            }

            if (body2.getY() > 840 || totalCount == 30) /* if the donut touched the border */ {
                // set the Y to the start
                body.setVisible(false);
                body1.setVisible(false);
                body2.setVisible(false);
                body3.setVisible(false);
                body4.setVisible(false);
                scoreBox.setVisible(false);
                remainingBox.setVisible(false);
                ranks.add(score);
                Collections.sort(ranks, Collections.reverseOrder());
                setTop5(Top5);
                pane.getChildren().add(getVbox(Top5));
                score = 0;
                pause();
            }
            // increasing the Y means moving downward
            body2.setY(y2++);


        }

        private void moveBall3() {


            body3.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    score += 4;
                    count3++;
                    totalCount++;
                    y3 = 25;
                    scoreText.setText("Score: " + score);
                    remainingText.setText("Remaining: " + (remaining - totalCount));
                    body3.setX(random() * 850);
                    body3.setY(25);
                    increaseSpeed3();

                }

            });

            if (count3 == 6) {
                animation3.pause();
                body3.setVisible(false);
            }

            if (body3.getY() > 850 || totalCount == 30) /* if the donut touched the border */ {
                // set the Y to the start
                body.setVisible(false);
                body1.setVisible(false);
                body2.setVisible(false);
                body3.setVisible(false);
                body4.setVisible(false);
                scoreBox.setVisible(false);
                remainingBox.setVisible(false);
                ranks.add(score);
                Collections.sort(ranks, Collections.reverseOrder());
                setTop5(Top5);
                pane.getChildren().add(getVbox(Top5));
                score = 0;
                pause();
            }
            // increasing the Y means moving downward
            body3.setY(y3++);


        }


        private void moveBall4() {


            body4.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    score += 5;
                    count4++;
                    totalCount++;
                    y4 = 25;
                    scoreText.setText("Score: " + score);
                    remainingText.setText("Remaining: " + (remaining - totalCount));
                    body4.setX(random() * 850);
                    body4.setY(25);
                    increaseSpeed4();
                }

            });

            if (count4 == 6) {
                animation4.pause();
                body4.setVisible(false);
            }

            if (body4.getY() > 860 || totalCount == 30) /* if the donut touched the border */ {
                // set the Y to the start
                body.setVisible(false);
                body1.setVisible(false);
                body2.setVisible(false);
                body3.setVisible(false);
                body4.setVisible(false);
                scoreBox.setVisible(false);
                remainingBox.setVisible(false);
                ranks.add(score);
                Collections.sort(ranks, Collections.reverseOrder());
                setTop5(Top5);
                pane.getChildren().add(getVbox(Top5));
                score = 0;
                pause();
            }
            // increasing the Y means moving downward
            body4.setY(y4++);


        }

    }

    public void rules() {
        start.setVisible(false);
        rulesScreen.setVisible(true);
        rulesScreen.setFitHeight(pane.getHeight());
        rulesScreen.setFitWidth(pane.getWidth());
        rulesScreen.setOpacity(0.9);
        pane.getChildren().add(rulesScreen);

        rulesScreen.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                rulesScreen.setVisible(false);
                starting();
            }
        });

    }

    public void starting() {
        // when the game start the start screen disappear and the donut start the animation
        donut.play();
        pane.getChildren().addAll(scoreBox, remainingBox, donut);
    }

    public void restarting(VBox vBox) {
        vBox.setVisible(false);
        restartMenu.setVisible(false);
        remaining = 30;
        y = 25;
        y1 = 25;
        y2 = 25;
        y3 = 25;
        y4 = 25;
        body.setY(25);
        body1.setY(25);
        body2.setY(25);
        body3.setY(25);
        body4.setY(25);
        donut.play();
    }

}