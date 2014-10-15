package controller;

/*Copyright 2004-2005 Univ.Prof. Dipl.-Ing. Dr.techn. Wolfgang SLANY,
 Andreas Augustin, Sandra Durasiewicz, Bojan Hrnkas, Markus K�erl,
 Bernhard Kornberger, Susanne Sch�erl

 This file is part of Neptune-Robot-Simulation.

 Neptune-Robot-Simulation is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 Neptune-Robot-Simulation is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Neptune-Robot-Simulation; if not, write to the Free Software
 Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  US
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;

public class SqlCommunicator {
    static private String temp_buffer_ = "";

    static private boolean activated_ = false;

    static private int log_level_;
    static private URL script_file_;
    static private String user_id_;
    static private String level_;
    static private String cookie_;
    static private boolean use_cookie_ = false;

    static public void activate(boolean activate) {
        activated_ = activate;
    }

    static public void saveRules(URL script_file, String user_id, int level,
            int lab_id, int pub, String text) {
        if (activated_) {
            URLConnection urlConn = null;
            try {
                urlConn = script_file.openConnection();
            } catch (Exception w) {
                System.out
                        .println("Rule writing failed! (openConnecton() failed)");
            }
            try {
                urlConn.setDoInput(true);
                urlConn.setUseCaches(false);

            } catch (Exception a) {
                System.out
                        .println("Rule writing failed! (setDoInput() failed)");
            }
            try {
                urlConn.setDoOutput(true);
                PrintWriter out = new PrintWriter(urlConn.getOutputStream());
                out.println("user_id=" + user_id + "&level="
                        + Integer.toString(level) + "&lab_id="
                        + Integer.toString(lab_id) + "&public="
                        + Integer.toString(pub) + "&text="
                        + URLEncoder.encode(text, "UTF-8"));
                out.close();
                // Get response data.
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(urlConn.getInputStream()));
                String str;
                while (null != ((str = input.readLine()))) {
                    System.out.println(str);
                }
                input.close();
            } catch (Exception a) {
                System.out.println("Rule writing failed! " + a.getMessage());
            }
        }
    }

    static public void add_log(String log_entry, int level) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getDefault());
        String timestamp = getTimestamp(cal);
        // flush if buffer is already lager then 20000 chars
        if (temp_buffer_.length() > 20000) {
            flush_log();
        }
        if ((log_level_ & level) != 0)
            temp_buffer_ += timestamp + " (" + Integer.toString(level) + "): "
                    + log_entry + "\n";
    }

    static public void init(String cookie, URL script_file, String user_id,
            int loglevel) {
        cookie_ = cookie;
        script_file_ = script_file;
        user_id_ = user_id;
        log_level_ = loglevel;
    }

    static public void setLevel(String level) {
        level_ = level;
    }

    static public void flush_log() {
        // System.out.println("flush_log("+script_file_+", "+user_id_+", "+level+")");
        flush_log(script_file_, user_id_, level_);
    }

    static public void flush_log(URL script_file, String user_id, String level) {
        if (activated_) {
            URLConnection urlConn = null;
            try {
                urlConn = script_file.openConnection();
                if (use_cookie_ == true) {
                    urlConn.setRequestProperty("Cookie", cookie_);
                    System.out.println("Use cookei: " + cookie_);
                } else {
                    System.out.println("Try connection without cookie");
                }
            } catch (Exception w) {
                // System.out.println("1 URL: "+script_file.toString());
                System.out
                        .println("Log writing failed! (openConnecton() failed)");
            }
            try {
                urlConn.setDoInput(true);
                // urlConn.setUseCaches(false);
                urlConn.setUseCaches(true);// because of the cookie!

            } catch (Exception a) {
                // System.out.println("2 URL: "+script_file.toString());
                System.out.println("Log writing failed! (setDoInput() failed)");
            }
            try {
                urlConn.setDoOutput(true);
                PrintWriter out = new PrintWriter(urlConn.getOutputStream());
                out.println("userid=" + user_id + "&level=" + level + "&text="
                        + URLEncoder.encode(temp_buffer_, "UTF-8"));
                out.close();
                // Get response data.
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(urlConn.getInputStream()));
                String str;
                while (null != ((str = input.readLine()))) {
                    System.out.println(str);
                    if (use_cookie_ == false) {
                        // System.out.println("Next time use cookie");
                        // use_cookie_=true;
                    }
                }
                input.close();
                temp_buffer_ = "";
            } catch (Exception a) {
                // System.out.println("3 URL: "+script_file.toString());
                System.out.println("Log writing failed! " + a.getMessage());
                if (use_cookie_ == false) {
                    // System.out.println("Next time use cookie");
                    // use_cookie_=true;
                }
            }
        } else {
            temp_buffer_ = "";
        }
    }

    static private String getTimestamp(GregorianCalendar cal) {
        String timestamp = "";

        timestamp = expandNumber(Integer.toString(cal.get(Calendar.YEAR)), 2)
                + "-"
                + expandNumber(Integer.toString(cal.get(Calendar.MONTH) + 1), 2)
                + "-"
                + expandNumber(Integer.toString(cal.get(Calendar.DATE)), 2)
                + " "
                + expandNumber(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)),
                        2) + ":"
                + expandNumber(Integer.toString(cal.get(Calendar.MINUTE)), 2)
                + ":"
                + expandNumber(Integer.toString(cal.get(Calendar.SECOND)), 2);

        return timestamp;
    }

    static private String expandNumber(String toExpand, int places) {
        while (toExpand.length() < places)
            toExpand = "0" + toExpand;
        return toExpand;
    }
}
