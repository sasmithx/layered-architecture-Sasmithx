package com.example.layeredarchitecture.view.controller;

import com.example.layeredarchitecture.view.bo.BOFactory;
import com.example.layeredarchitecture.view.bo.custom.ItemBO;
import com.example.layeredarchitecture.view.dto.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ManageItemsFormController {
    public AnchorPane root;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TableView<ItemTM> tblItems;
    public TextField txtUnitPrice;
    public JFXButton btnAddNewItem;

//    ItemDAO itemDAO = new ItemDAOImpl(); //property injection
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getTypes(BOFactory.BOTypes.ITEM);

    public void initialize() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        initUI();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");

                txtCode.setDisable(false);
                txtDescription.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
    }

    private void loadAllItems() {
        tblItems.getItems().clear();
        try {
            /*Get all items*/

            ArrayList<ItemDTO> allItems = itemBO.getAllItem();

            for (ItemDTO itemDTO : allItems) {
                tblItems.getItems().add(
                        new ItemTM(
                                itemDTO.getCode(),
                                itemDTO.getDescription(),
                                itemDTO.getUnitPrice(),
                                itemDTO.getQtyOnHand()));
            }

            /*while (rst.next()) {
                tblItems.getItems().add(new ItemTM(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
            }*/
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/grb/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCode.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtCode.clear();
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        /*Delete Item*/
        String code = tblItems.getSelectionModel().getSelectedItem().getCode();
        try {
            if (!existItem(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
            }
            /*Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
            pstm.setString(1, code);
            pstm.executeUpdate();*/

            boolean isDeleted = itemBO.deleteItem(code);

            if (isDeleted) {
                tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
                tblItems.getSelectionModel().clearSelection();
                initUI();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String description = txtDescription.getText();

        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtDescription.requestFocus();
            return;
        } else if (!txtUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtUnitPrice.requestFocus();
            return;
        } else if (!txtQtyOnHand.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtQtyOnHand.requestFocus();
            return;
        }

        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);


        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }
                //Save Item
                /*Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
                pstm.setString(1, code);
                pstm.setString(2, description);
                pstm.setBigDecimal(3, unitPrice);
                pstm.setInt(4, qtyOnHand);
                pstm.executeUpdate();*/

                boolean isSaved = itemBO.saveItem(new ItemDTO(code, description, unitPrice, qtyOnHand));

                if (isSaved) {
                    tblItems.getItems().add(new ItemTM(code, description, unitPrice, qtyOnHand));
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                /*Update Item*/
                /*Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
                pstm.setString(1, description);
                pstm.setBigDecimal(2, unitPrice);
                pstm.setInt(3, qtyOnHand);
                pstm.setString(4, code);
                pstm.executeUpdate();*/

                boolean isUpdated = itemBO.updateItem(new ItemDTO(code, description, unitPrice, qtyOnHand));

                if (isUpdated) {
                    ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                    selectedItem.setDescription(description);
                    selectedItem.setQtyOnHand(qtyOnHand);
                    selectedItem.setUnitPrice(unitPrice);
                    tblItems.refresh();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewItem.fire();
    }


    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/

        return itemBO.existItem(code);
    }


    private String generateNewId() {
        try {
            /*Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");*/

            ResultSet resultSet = itemBO.generateNewItemId();

            if (resultSet.next()) {
                String id = resultSet.getString("code");
                int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
                return String.format("I00-%03d", newItemId);
            } else {
                return "I00-001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";
    }
}
