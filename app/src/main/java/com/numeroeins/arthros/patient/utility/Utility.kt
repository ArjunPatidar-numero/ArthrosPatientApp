package com.numeroeins.arthros.patient.utility

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.format.DateFormat
import android.view.View
import android.widget.LinearLayout
import androidx.core.graphics.drawable.DrawableCompat
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utility {


  fun getCurrentDateTime(): String? {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat(TIME_FORMAT_SPLASH_YYYY_MM_DD_HH_MM_AA, Locale.getDefault())
    return sdf.format(cal.time)
  }
  fun changeTimeFormatLongToDate(timelog: String): String  {
    val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
    cal.setTimeInMillis(java.lang.Long.parseLong(timelog))

    var date: String = DateFormat.format("yyyy-MM-dd HH:mm:ss", cal).toString()
    return date
  }
  fun calculateHrs(fromTime: String, toTime: String): String {
    val simpleDateFormat = SimpleDateFormat("hh:mma")
    val startDate: Date = simpleDateFormat.parse(fromTime)
    val endDate: Date = simpleDateFormat.parse(toTime)

    var difference: Long = endDate.getTime() - startDate.getTime()
    if (difference < 0) {
      val dateMax: Date = simpleDateFormat.parse("12:00")
      val dateMin: Date = simpleDateFormat.parse("00:00")
      difference = dateMax.getTime() - startDate.getTime() + (endDate.getTime() - dateMin.getTime())
    }
    val days = (difference / (1000 * 60 * 60 * 24)).toInt()
    val hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)).toInt()
    val min = (difference - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours).toInt() / (1000 * 60)

    var timeDate = ""+hours+"hrs "
    if(min>0)
    {
      timeDate += ""+min+"min"
    }
    return timeDate
  }

  fun getChangeDateFormat(date: String?, currentFormat: String?, requiredFormat: String?): String? {
    var newDateString = ""
    if (currentFormat != null && currentFormat.length > 0 && requiredFormat != null && requiredFormat.length > 0) {
      val dateObj: Date
      val input = SimpleDateFormat(currentFormat, Locale.US)
      val output = SimpleDateFormat(requiredFormat, Locale.US)
      try {
        dateObj = input.parse(date)
        newDateString = output.format(dateObj)
      } catch (e: ParseException) {
        e.printStackTrace()
      }
    }
    return newDateString
  }

  fun convertTimeStampIntoAgo(date: String?): String? {
    try {
    //  var thisDate = getChangeDateFormat(date, TIME_DATE_FORMAT_dd_MM_yyyy_with_TZ, "yyyy-MM-dd HH:mm:ss")

      val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      val dateObj = sdf.parse(date)
      val p = PrettyTime()
      return p.format(dateObj)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return ""
  }

  var grey:Int =1
  var pink = 2
  var white = 3
  var weekdays =
          arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
  var months = arrayOf(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
  )
  var daysOfMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  private var daysInMonth = 0
  var currentDayOfMonth = 0
  var currentWeekDay = 0

  /*fun calculateCalenderDate(mm: Int, yy: Int): ArrayList<CalenderModel>? {
    val tag = "tag"
    val list: ArrayList<String> = ArrayList()
    val calenderModelArrayList: ArrayList<CalenderModel> = ArrayList()
    val DAY_OFFSET = 1
    Log.d(tag, "==> printMonth: mm: $mm yy: $yy")
    var trailingSpaces = 0
    var daysInPrevMonth = 0
    var prevMonth = 0
    var prevYear = 0
    var nextMonth = 0
    var nextYear = 0
    val currentMonth = mm - 1
    val currentMonthName: String =months[currentMonth]
    daysInMonth = daysOfMonth[currentMonth]
    Log.d(
            tag,
            "Current Month:  $currentMonthName having $daysInMonth days."
    )
    val cal = GregorianCalendar(yy, currentMonth, 1)
    Log.d(tag, "Gregorian Calendar:= " + cal.time.toString())
    if (currentMonth == 11) {
      prevMonth = currentMonth - 1
      daysInPrevMonth = prevMonth
      nextMonth = 0
      prevYear = yy
      nextYear = yy + 1
      Log.d(
              tag,
              "*->PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear"
      )
    } else if (currentMonth == 0) {
      prevMonth = 11
      prevYear = yy - 1
      nextYear = yy
      daysInPrevMonth = daysOfMonth[prevMonth]
      nextMonth = 1
      Log.d(
              tag,
              "**--> PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear"
      )
    } else {
      prevMonth = currentMonth - 1
      nextMonth = currentMonth + 1
      nextYear = yy
      prevYear = yy
      daysInPrevMonth =daysOfMonth[prevMonth]
      Log.d(
              tag,
              "***---> PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear"
      )
    }
    val currentWeekDay = cal[Calendar.DAY_OF_WEEK] - 1
    trailingSpaces = currentWeekDay
    Log.d(
            tag, "Week Day:" + currentWeekDay + " is "
            + weekdays[currentWeekDay]
    )
    Log.d(tag, "No. Trailing space to Add: $trailingSpaces")
    Log.d(tag, "No. of Days in Previous Month: $daysInPrevMonth")
    if (cal.isLeapYear(cal[Calendar.YEAR])) if (mm == 2) ++daysInMonth else if (mm == 3) ++daysInPrevMonth

    // Trailing Month days
    for (i in 0 until trailingSpaces) {
      Log.d(
              tag,
              "PREV MONTH:= " + prevMonth + " => " + months[prevMonth] + " " + (daysInPrevMonth - trailingSpaces + DAY_OFFSET + i).toString()
      )
      list.add(
              (daysInPrevMonth - trailingSpaces + DAY_OFFSET + i).toString() + "-GREY" + "-" + months[prevMonth]
                      + "-" + prevYear
      )
      val calenderModel = CalenderModel()
      calenderModel.days = (daysInPrevMonth - trailingSpaces + DAY_OFFSET + i).toString()
      calenderModel.type = grey
      calenderModel.month = months[prevMonth]
      calenderModel.year = prevYear.toString() + ""
      calenderModelArrayList.add(calenderModel)
    }

    // Current Month Days
    for (i in 1..daysInMonth) {
      Log.d(
              currentMonthName,
              i.toString() + " " + months[currentMonth]  + " " + yy
      )
      if (i == currentDayOfMonth) {
        list.add(i.toString() + "-BLUE" + "-" + months[currentMonth]  + "-" + yy)
        val calenderModel = CalenderModel()
        calenderModel.type = pink
        calenderModel.days = i.toString()
        calenderModel.month = months[currentMonth]
        calenderModel.year = yy.toString() + ""
        calenderModelArrayList.add(calenderModel)
      } else {
        //month data
        list.add(i.toString() + "-WHITE" + "-" + months[currentMonth] + "-" + yy)
        val calenderModel = CalenderModel()
        calenderModel.type = white
        calenderModel.days = i.toString()
        calenderModel.month = months[currentMonth]
        calenderModel.year = yy.toString() + ""
        calenderModelArrayList.add(calenderModel)
      }
    }

    // Leading Month days

    // Leading Month days

    // Leading Month days
    val leading = list.size % 7
    for (i in 0 until leading) {
      Log.d(tag, "NEXT MONTH:= " +  months[nextMonth] )
      list.add((i + 1).toString() + "-GREY" + "-" +  months[nextMonth]  + "-" + nextYear)
      val calenderModel = CalenderModel()
      calenderModel.type = grey
      calenderModel.days = (i + 1).toString()
      calenderModel.month = months[nextMonth]
      calenderModel.year = nextYear.toString() + ""
      calenderModelArrayList.add(calenderModel)
    }

    *//*  val leading = list.size % 7
    for (var i = 0 i < leading; i++) {
        Log.d(tag, "NEXT MONTH:= " +  months[nextMonth] )
        list.add((i + 1).toString() + "-GREY" + "-" +  months[nextMonth]  + "-" + nextYear)
        val calenderModel = CalenderModel()
        calenderModel.type = grey
        calenderModel.days = (i + 1).toString()
        calenderModel.month = months[nextMonth]
        calenderModel.year = nextYear.toString() + ""
        calenderModelArrayList.add(calenderModel)
    }*//*
    return calenderModelArrayList
  }
*/
  /* public static void changeBgStrokeColor(LinearLayout school, int colors) {
      GradientDrawable bgShape = (GradientDrawable) school.getBackground();
      //bgShape.setColor(colors);
      bgShape.setStroke(3, colors);
  }*/
  fun setBgWithOutStrokeColor(school: LinearLayout, colors: Int) {
    val bgShape = school.background as GradientDrawable
    bgShape.setColor(colors)
    //  bgShape.setStroke(1, colors);
  }

 /* fun calculateCalenderTimeSlot(startTime: String, endTime: String, date: String): ArrayList<MinutesListModel>? {
    val slotModelArrayList: ArrayList<MinutesListModel> = ArrayList()

    //val firstTime = "00:00 AM"
    //   val secondDate = "26/02/2019"
    //    val secondTime = "12:00 PM"
    // val format = "dd/MM/yyyy hh:mm a"
    val sdf = SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_AA ,Locale.getDefault())
    val dateObj1 = sdf.parse(date + " " + startTime)
    val dateObj2 = sdf.parse(date + " " + endTime)
    println("Date Start: " + dateObj1)
    println("Date End: " + dateObj2)
    var dif = dateObj1.getTime()
    while (dif < dateObj2.getTime())
    {
      val slot = Date(dif)

      val formatter = SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM__AA,Locale.getDefault())
      val strDate = formatter.format(slot)
      try {
        // val strs = strDate.split("\\s+").toTypedArray()
        val split = strDate.split(" ".toRegex())
        //val splited: List<String> = strDate.split("\\s+")
        val minutesListModel=MinutesListModel()

        minutesListModel.slotTime=split[1]+" "+split[2].toUpperCase()
        minutesListModel.date= split[0]
        slotModelArrayList.add(minutesListModel)
      }catch (e: ParseException) {
        e.printStackTrace()
      }

      dif += 1800000
    }
    return slotModelArrayList
  }
  fun calculateCalenderTimeSlotPlus(startTime: String, endTime: String, date: String): ArrayList<MinutesListModel>? {
    val slotModelArrayList: ArrayList<MinutesListModel> = ArrayList()

    //val firstTime = "00:00 AM"
    //   val secondDate = "26/02/2019"
    //    val secondTime = "12:00 PM"
    // val format = "dd/MM/yyyy hh:mm a"
    val sdf = SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_AA ,Locale.getDefault())
    val dateObj1 = sdf.parse(date + " " + startTime)
    val dateObj2 = sdf.parse(date + " " + endTime)
    println("Date Start: " + dateObj1)
    println("Date End: " + dateObj2)
    var dif = dateObj1.getTime()
    while (dif <= dateObj2.getTime())
    {
      val slot = Date(dif)

      val formatter = SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM__AA,Locale.getDefault())
      val strDate = formatter.format(slot)
      try {
        // val strs = strDate.split("\\s+").toTypedArray()
        val split = strDate.split(" ".toRegex())
        //val splited: List<String> = strDate.split("\\s+")
        val minutesListModel=MinutesListModel()
        minutesListModel.slotTime=split[1]+" "+split[2].toUpperCase()
        minutesListModel.date= split[0]
        slotModelArrayList.add(minutesListModel)

      }catch (e: ParseException) {
        e.printStackTrace()
      }

      dif += 1800000
    }
    return slotModelArrayList
  }*/


  ////////////////////////////////////////


 /* fun readFile(context: Context): ArrayList<PDFGalleryModel>? {
    val data: ArrayList<PDFGalleryModel> = ArrayList()
    data.clear()
    val pdf = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")
    val doc = MimeTypeMap.getSingleton().getMimeTypeFromExtension("doc");
    val docx = MimeTypeMap.getSingleton().getMimeTypeFromExtension("docx");
    val xls = MimeTypeMap.getSingleton().getMimeTypeFromExtension("xls");
    val xlsx = MimeTypeMap.getSingleton().getMimeTypeFromExtension("xlsx");
    val ppt = MimeTypeMap.getSingleton().getMimeTypeFromExtension("ppt");
    val pptx = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pptx");
    val txt = MimeTypeMap.getSingleton().getMimeTypeFromExtension("txt");
    //  String rtx = MimeTypeMap.getSingleton().getMimeTypeFromExtension("rtx");
    //   String rtf = MimeTypeMap.getSingleton().getMimeTypeFromExtension("rtf");
    //  String html = MimeTypeMap.getSingleton().getMimeTypeFromExtension("html");

    //Table
    val table: Uri = MediaStore.Files.getContentUri("external")
    //Column
    val column = arrayOf(
      MediaStore.Files.FileColumns.DATA,
      MediaStore.Files.FileColumns.TITLE,
      MediaStore.Files.FileColumns.MIME_TYPE,
      MediaStore.Files.FileColumns.MEDIA_TYPE,
      MediaStore.Files.FileColumns.DISPLAY_NAME,
      MediaStore.Files.FileColumns._ID
    )
    //Where
    val where = (MediaStore.Files.FileColumns.MIME_TYPE + "=?" + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?" + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?" + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?" + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?" + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            + " OR " + MediaStore.Files.FileColumns.MIME_TYPE + "=?")
    //args
    val args = arrayOf(pdf , doc, docx, xls, xlsx, ppt, pptx, txt*//*, rtx, rtf, html*//*)
    val fileCursor: Cursor = context.getContentResolver().query(table, column, where, args, null)!!
    for (i in 0 until fileCursor.count) {
      val documentGalleryModel = PDFGalleryModel()
      fileCursor.moveToPosition(i)
      val displayName: String = fileCursor.getString(fileCursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME))
      val title: String = fileCursor.getString(fileCursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE))
      val Path: String = fileCursor.getString(fileCursor.getColumnIndex(MediaStore.Files.FileColumns.DATA))
      val mimeType: String = fileCursor.getString(fileCursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE))
      val type = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
      if (!TextUtils.isEmpty(displayName)) {
        documentGalleryModel.name = displayName
      } else {
        documentGalleryModel.name = title
      }
      documentGalleryModel.path = Path
      documentGalleryModel.type = type
      data.add(documentGalleryModel)
    }
    return data
  }*/

  /* public static void changeBgStrokeColor(LinearLayout school, int colors) {
        GradientDrawable bgShape = (GradientDrawable) school.getBackground();
        //bgShape.setColor(colors);
        bgShape.setStroke(3, colors);
    }*/
  @SuppressLint("UseCompatLoadingForDrawables")
  fun setBgColor(context: Context, school: View, colors: Int) {

    val wrappedDrawable: Drawable = DrawableCompat.wrap(school.background)
    if (wrappedDrawable != null) {
      DrawableCompat.setTint(wrappedDrawable.mutate(), colors)
      setBackgroundDrawable(school, wrappedDrawable)
    }
    //  bgShape.setStroke(1, colors);
  }
  fun setBackgroundDrawable(view: View, drawable: Drawable?) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
      view.background = drawable
    } else {
      view.background = drawable
    }
  }
  fun dateToTimeFormat(oldstringDate: String?): String? {
    val p = PrettyTime(Locale.ENGLISH)
    var isTime: String? = ""
    try {
      val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      // sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      val date = sdf.parse(oldstringDate)
      isTime = p.format(date)
    } catch (e: ParseException) {
      e.printStackTrace()
    }
    return isTime
  }

}