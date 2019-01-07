package de.htwg.se.NineMensMorris.a_view.Gui

import swing._

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImagePanel extends Panel {
  private var _imagePath = ""
  private var bufferedImage:BufferedImage = _

  def imagePath: String = _imagePath

  def imagePath_=(value:String)
  {
    _imagePath = value
    bufferedImage = ImageIO.read(new File(_imagePath))
  }


  override def paintComponent(g:Graphics2D): Unit =
  {
    if (null != bufferedImage) g.drawImage(bufferedImage, 0, 0, null)
  }
}