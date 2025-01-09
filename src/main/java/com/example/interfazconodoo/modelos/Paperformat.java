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
 * Clase en la que tenemos los campos que encontramos en la tabla "report_paperformat".
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
   * Constructor con los parametros que se van a usar.
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

  /**
   * Obtener el id del formato.
   *
   * @return El id del formato.
   */

  public IntegerProperty idProperty() {
    return id;
  }

  public int getId() {
    return id.get();
  }

  /**
   * Insertar el id del formato.
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
   * Obtener el nombre del formato.
   *
   * @return El nombre del formato.
   */

  public StringProperty nameProperty() {
    return name;
  }

  public String getName() {
    return name.get();
  }

  /**
   * Insertar el nombre del formato.
   */

  public void setName(String name) {
    this.name.set(name);
  }

  /**
   * Obtener el formato del formato.
   *
   * @return El formato del formato.
   */

  public StringProperty formatProperty() {
    return format;
  }

  public String getFormat() {
    return format.get();
  }

  /**
   * Inseratr el nombre del formato.
   */

  public void setFormat(String format) {
    this.format.set(format);
  }

  /**
   * Obtener la orientación del formato.
   *
   * @return La orientación del formato.
   */

  public StringProperty orientationProperty() {
    return orientation;
  }

  public String getOrientation() {
    return orientation.get();
  }

  /**
   * Insertar la orientación del formato.
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
   * Obtener el margen superior del formato.
   *
   * @return El margen superior del formato.
   */

  public DoubleProperty marginTopProperty() {
    return marginTop;
  }

  public double getMarginTop() {
    return marginTop.get();
  }

  /**
   * Inserta el margen superior del formato.
   */

  public void setMarginTop(double marginTop) {
    this.marginTop.set(marginTop);
  }

  /**
   * Obtener el margen inferior del formato.
   *
   * @return El margen inferior del formato.
   */

  public DoubleProperty marginBottomProperty() {
    return marginBottom;
  }

  public double getMarginBottom() {
    return marginBottom.get();
  }

  /**
   * Insertar el margen inferior del formato.
   */

  public void setMarginBottom(double marginBottom) {
    this.marginBottom.set(marginBottom);
  }

  /**
   * Obtener el margen izquierdo del formato.
   *
   * @return El margen izquierdo del formato.
   */

  public DoubleProperty marginLeftProperty() {
    return marginLeft;
  }

  public double getMarginLeft() {
    return marginLeft.get();
  }

  /**
   * Insertar el margen izquierdo del formato.
   */

  public void setMarginLeft(double marginLeft) {
    this.marginLeft.set(marginLeft);
  }

  /**
   * Obtener el margen derecho del formato.
   *
   * @return El margen derecho del formato.
   */

  public DoubleProperty marginRightProperty() {
    return marginRight;
  }

  public double getMarginRight() {
    return marginRight.get();
  }

  /**
   * Insertar el margen derecho del formato.
   */

  public void setMarginRight(double marginRight) {
    this.marginRight.set(marginRight);
  }

}

