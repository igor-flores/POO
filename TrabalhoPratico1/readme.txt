Para compilar o código insira o comando abaixo na src/ do projeto. 

LINUX
javac --module-path "../javafx-linux/" --add-modules javafx.swt,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing Controller/*.java Model/*.java;  java --module-path "../javafx-linux/" --add-modules javafx.swt,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing Controller.Main

WINDOWS
javac --module-path "..\javafx-windows\lib\" --add-modules javafx.swt,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing Controller/*.java Model/*.java;  java --module-path "..\javafx-windows\lib\" --add-modules javafx.swt,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.swing Controller.Main
