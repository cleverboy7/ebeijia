package com.ebeijia.api.face

/**
 * Face
 * @author zhicheng.xu
 *         15/8/19
 */

class Face extends Comparable[Face] {
  private var faceId: String = null
  private var ageValue: Int = 0
  private var ageRange: Int = 0
  private var genderValue: String = null
  private var genderConfidence: Double = .0
  private var raceValue: String = null
  private var raceConfidence: Double = .0
  private var smilingValue: Double = .0
  private var centerX: Double = .0
  private var centerY: Double = .0

  def setFaceId(faceId: String) {
    this.faceId = faceId
  }

  def getAgeValue: Int = {
    ageValue
  }

  def setAgeValue(ageValue: Int) {
    this.ageValue = ageValue
  }

  def setAgeRange(ageRange: Int) {
    this.ageRange = ageRange
  }

  def getGenderValue: String = {
    genderValue
  }

  def setGenderValue(genderValue: String) {
    this.genderValue = genderValue
  }

  def setGenderConfidence(genderConfidence: Double) {
    this.genderConfidence = genderConfidence
  }

  def getRaceValue: String = {
    raceValue
  }

  def setRaceValue(raceValue: String) {
    this.raceValue = raceValue
  }

  def setRaceConfidence(raceConfidence: Double) {
    this.raceConfidence = raceConfidence
  }

  def getSmilingValue: Double = {
    smilingValue
  }

  def setSmilingValue(smilingValue: Double) {
    this.smilingValue = smilingValue
  }

  def getCenterX: Double = {
    centerX
  }

  def setCenterX(centerX: Double) {
    this.centerX = centerX
  }

  def getCenterY: Double = {
    centerY
  }

  def setCenterY(centerY: Double) {
    this.centerY = centerY
  }

  def getFaceId: String = {
    faceId
  }

  def compareTo(face: Face): Int = {
    var result: Int = 0
    if (this.getCenterX > face.getCenterX) result = 1
    else result = -1
    result
  }
}

