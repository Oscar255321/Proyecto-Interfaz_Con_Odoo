package com.example.interfazconodoo.modelos;

import java.sql.Timestamp;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase que representa la estructura de la tabla "report_paperformat" en la base de datos.
 * Contiene propiedades con sus respectivos métodos de acceso y modificación.
 *
 * <p>Esta clase utiliza propiedades de JavaFX para facilitar la
 * vinculación con la interfaz gráfica.</p>
 *
 * @author Oscar Abellan
 * @version 1.0
 */

public class Paperformat {

  private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
  private final IntegerProperty pageHeight = new SimpleIntegerProperty(this, "pageHeight");
  private final IntegerProperty pageWidth = new SimpleIntegerProperty(this, "pageWidth");
  private final IntegerProperty headerSpacing = new SimpleIntegerProperty(this, "headerSpacing");
  private final IntegerProperty dpi = new SimpleIntegerProperty(this, "dpi");
  private final IntegerProperty createUid = new SimpleIntegerProperty(this, "createUid");
  private final IntegerProperty writeUid = new SimpleIntegerProperty(this, "writeUid");

  private final StringProperty name = new SimpleStringProperty(this, "name");
  private final StringProperty format = new SimpleStringProperty(this, "format");
  private final StringProperty orientation = new SimpleStringProperty(this, "orientation");

  private final BooleanProperty defecto = new SimpleBooleanProperty(this, "default_");
  private final BooleanProperty headerLine = new SimpleBooleanProperty(this, "headerLine");
  private final BooleanProperty disableShrinking = new SimpleBooleanProperty(this,
      "disableShrinking");

  private final ObjectProperty<Timestamp> createDate = new SimpleObjectProperty<>(this,
      "createDate");
  private final ObjectProperty<Timestamp> writeDate = new SimpleObjectProperty<>(this, "writeDate");

  private final DoubleProperty marginTop = new SimpleDoubleProperty(this, "marginTop");
  private final DoubleProperty marginBottom = new SimpleDoubleProperty(this, "marginBottom");
  private final DoubleProperty marginLeft = new SimpleDoubleProperty(this, "marginLeft");
  private final DoubleProperty marginRight = new SimpleDoubleProperty(this, "marginRight");

  /**
   * Constructor con parámetros.
   *
   * @param id  Identificador del formato.
   * @param name  Nombre del formato.
   * @param format  Formato de la página (Ejemplo: A4, Letter, etc.).
   * @param orientation  Orientación del formato (Portrait o Landscape).
   * @param marginTop  Margen superior.
   * @param marginBottom  Margen inferior.
   * @param marginLeft  Margen izquierdo.
   * @param marginRight  Margen derecho.
   */

  public Paperformat(Integer id, String name, String format, String orientation, Double marginTop,
      Double marginBottom, Double marginLeft, Double marginRight) {
    this.id.set(id);
    this.name.set(name);
    this.format.set(format);
    this.orientation.set(orientation);
    this.marginTop.set(marginTop);
    this.marginBottom.set(marginBottom);
    this.marginLeft.set(marginLeft);
    this.marginRight.set(marginRight);
  }

  /**
   * Constructor vacio.
   */

  public Paperformat() {
  }

  public IntegerProperty idProperty() {
    return id;
  }

  /**
   * Obtiene el identificador del formato.
   *
   * @return ID del formato.
   */

  public int getId() {
    return id.get();
  }

  /**
   * Establece el identificador del formato.
   *
   * @param id Nuevo ID del formato.
   */

  public void setId(int id) {
    this.id.set(id);
  }

  public IntegerProperty pageHeightProperty() {
    return pageHeight;
  }

  public int getPageHeight() {
    return pageHeight.get();
  }

  public void setPageHeight(int pageHeight) {
    this.pageHeight.set(pageHeight);
  }

  public IntegerProperty pageWidthProperty() {
    return pageWidth;
  }

  public int getPageWidth() {
    return pageWidth.get();
  }

  public void setPageWidth(int pageWidth) {
    this.pageWidth.set(pageWidth);
  }

  public IntegerProperty headerSpacingProperty() {

    return headerSpacing;
  }

  public int getHeaderSpacing() {

    return headerSpacing.get();
  }

  public void setHeaderSpacing(int headerSpacing) {

    this.headerSpacing.set(headerSpacing);
  }

  public IntegerProperty dpiProperty() {

    return dpi;
  }

  public int getDpi() {

    return dpi.get();
  }

  public void setDpi(int dpi) {

    this.dpi.set(dpi);
  }

  public IntegerProperty createUidProperty() {

    return createUid;
  }

  public int getCreateUid() {

    return createUid.get();
  }

  public void setCreateUid(int createUid) {

    this.createUid.set(createUid);
  }

  public IntegerProperty writeUidProperty() {

    return writeUid;
  }

  public int getWriteUid() {

    return writeUid.get();
  }

  public void setWriteUid(int writeUid) {

    this.writeUid.set(writeUid);
  }

  /**
   * Obtiene la propiedad name, útil para enlazarla con UI en JavaFX.
   *
   * @return Propiedad del nombre del formato.
   */

  public StringProperty nameProperty() {

    return name;
  }

  /**
   * Obtiene el nombre del formato.
   *
   * @return Nombre del formato.
   */

  public String getName() {

    return name.get();
  }

  /**
   * Establece el nombre del formato.
   *
   * @param name Nuevo nombre del formato.
   */

  public void setName(String name) {

    this.name.set(name);
  }

  /**
   * Obtiene la propiedad del formato del papel.
   *
   * @return Propiedad del formato.
   */

  public StringProperty formatProperty() {

    return format;
  }

  /**
   * Obtiene el tipo de formato de la página (Ejemplo: A4, Letter, etc.).
   *
   * @return Formato del papel.
   */

  public String getFormat() {

    return format.get();
  }

  /**
   * Establece el tipo de formato del papel.
   *
   * @param format Nuevo tipo de formato del papel.
   */

  public void setFormat(String format) {

    this.format.set(format);
  }

  /**
   * Obtiene la propiedad de la orientación del papel.
   *
   * @return Propiedad de la orientación.
   */

  public StringProperty orientationProperty() {

    return orientation;
  }

  /**
   * Obtiene la orientación del papel (Portrait o Landscape).
   *
   * @return Orientación del formato.
   */

  public String getOrientation() {

    return orientation.get();
  }

  /**
   * Establece la orientación del papel.
   *
   * @param orientation Nueva orientación del formato.
   */

  public void setOrientation(String orientation) {

    this.orientation.set(orientation);
  }

  public BooleanProperty defaultProperty() {

    return defecto;
  }

  public boolean isDefault() {

    return defecto.get();
  }

  public void setDefault(boolean defecto) {

    this.defecto.set(defecto);
  }

  public BooleanProperty headerLineProperty() {

    return headerLine;
  }

  public boolean isHeaderLine() {

    return headerLine.get();
  }

  public void setHeaderLine(boolean headerLine) {

    this.headerLine.set(headerLine);
  }

  public BooleanProperty disableShrinkingProperty() {

    return disableShrinking;
  }

  public boolean isDisableShrinking() {
    return disableShrinking.get();
  }

  public void setDisableShrinking(boolean disableShrinking) {
    this.disableShrinking.set(disableShrinking);
  }

  public ObjectProperty<Timestamp> createDateProperty() {

    return createDate;
  }

  public Timestamp getCreateDate() {

    return createDate.get();
  }

  public void setCreateDate(Timestamp createDate) {

    this.createDate.set(createDate);
  }

  public ObjectProperty<Timestamp> writeDateProperty() {

    return writeDate;
  }

  public Timestamp getWriteDate() {

    return writeDate.get();
  }

  public void setWriteDate(Timestamp writeDate) {

    this.writeDate.set(writeDate);
  }

  /**
   * Obtiene la propiedad del margen superior.
   *
   * @return Propiedad del margen superior.
   */

  public DoubleProperty marginTopProperty() {

    return marginTop;
  }

  /**
   * Obtiene el margen superior.
   *
   * @return Margen superior en unidades de medida de la base de datos.
   */

  public double getMarginTop() {

    return marginTop.get();
  }

  /**
   * Establece el margen superior.
   *
   * @param marginTop Nuevo valor para el margen superior.
   */

  public void setMarginTop(double marginTop) {

    this.marginTop.set(marginTop);
  }

  /**
   * Obtiene la propiedad del margen inferior.
   *
   * @return Propiedad del margen inferior.
   */

  public DoubleProperty marginBottomProperty() {

    return marginBottom;
  }

  /**
   * Obtiene el margen inferior.
   *
   * @return Margen inferior en unidades de medida de la base de datos.
   */

  public double getMarginBottom() {

    return marginBottom.get();
  }

  /**
   * Establece el margen inferior.
   *
   * @param marginBottom Nuevo valor para el margen inferior.
   */

  public void setMarginBottom(double marginBottom) {

    this.marginBottom.set(marginBottom);
  }

  /**
   * Obtiene la propiedad del margen izquierdo.
   *
   * @return Propiedad del margen izquierdo.
   */

  public DoubleProperty marginLeftProperty() {

    return marginLeft;
  }

  /**
   * Obtiene el margen izquierdo.
   *
   * @return Margen izquierdo en unidades de medida de la base de datos.
   */

  public double getMarginLeft() {

    return marginLeft.get();
  }

  /**
   * Establece el margen izquierdo.
   *
   * @param marginLeft Nuevo valor para el margen izquierdo.
   */

  public void setMarginLeft(double marginLeft) {

    this.marginLeft.set(marginLeft);
  }

  /**
   * Obtiene la propiedad del margen derecho.
   *
   * @return Propiedad del margen derecho.
   */

  public DoubleProperty marginRightProperty() {

    return marginRight;
  }

  /**
   * Obtiene el margen derecho.
   *
   * @return Margen derecho en unidades de medida de la base de datos.
   */

  public double getMarginRight() {

    return marginRight.get();
  }

  /**
   * Establece el margen derecho.
   *
   * @param marginRight Nuevo valor para el margen derecho.
   */

  public void setMarginRight(double marginRight) {

    this.marginRight.set(marginRight);
  }

}

