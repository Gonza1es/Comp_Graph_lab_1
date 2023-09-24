package com.example.lab_1;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {


    public Canvas canvas;
    public TextField ax;
    public TextField bx;
    public TextField cx;
    public TextField ay;
    public TextField by;
    public TextField cy;
    public TextField scale;
    public Button draw;
    public Button turnX;
    public Button turnY;
    public Button turnXY;

    private final double start = 300;

    private Double[][] matrix = new Double[3][2];


    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawArea(gc);

        draw.setOnAction(actionEvent -> {
            matrix[0][0] = validate(ax.getText());
            matrix[0][1] = validate(ay.getText());
            matrix[1][0] = validate(bx.getText());
            matrix[1][1] = validate(by.getText());
            matrix[2][0] = validate(cx.getText());
            matrix[2][1] = validate(cy.getText());

            scale();

            draw(gc, matrix);
        });

        turnX.setOnAction(actionEvent -> {
            matrix = TransformUtils.transform(matrix, TransformType.TURN_X);
            draw(gc, matrix);
        });

        turnY.setOnAction(actionEvent -> {
            matrix = TransformUtils.transform(matrix, TransformType.TURN_Y);
            draw(gc, matrix);
        });

        turnXY.setOnAction(actionEvent -> {
            matrix = TransformUtils.transform(matrix, TransformType.TURN_XY);
            draw(gc, matrix);
        });
    }



    private void draw(GraphicsContext gc, Double[][] matrix) {

        gc.clearRect(0, 0 , canvas.getWidth(), canvas.getHeight());
        drawArea(gc);

        gc.strokeLine(start + matrix[0][0], start - matrix[0][1], start + matrix[1][0], start - matrix[1][1]);
        gc.strokeLine(start + matrix[1][0], start - matrix[1][1], start + matrix[2][0], start - matrix[2][1]);
        gc.strokeLine(start + matrix[2][0], start - matrix[2][1], start + matrix[0][0], start - matrix[0][1]);
    }

    private void drawArea(GraphicsContext gc) {

        gc.strokeLine(300, 0, 300, 600);
        gc.strokeLine(0, 300, 600, 300);

        for (int i = 0; i <= 600; i = i+10) {
            gc.strokeLine(i, 298, i, 302);
            gc.strokeLine(298, i, 302, i);
        }
    }

    private void scale() {
        if (!scale.getText().isBlank())
            matrix = TransformUtils.scaleMatrix(matrix, validate(scale.getText()));
    }

    private Double validate(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Неверный формат данных");
            alert.showAndWait();

            throw new RuntimeException();
        }
    }
}