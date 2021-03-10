Para compilar o código insira o comando abaixo na src/ do projeto.
javac --module-path "../javafx/" --add-modules javafx.swt,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing Controller/*.java Model/*.java;  java --module-path "../javafx/" --add-modules javafx.swt,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing Controller.Main

