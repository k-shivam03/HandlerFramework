package com.handler.framework.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.net.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;

import com.handler.framework.*;
import com.handler.framework.annotation.*;

public class TMService extends HttpServlet {

    HashMap<String,Service> map;
    //initializer block
    {
        try {
            map = populateDataStructure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private HashMap<String,Service> populateDataStructure() throws Exception {

        HashMap<String,Service> hMap = new HashMap<>();

        Class[] cl = getClasses("com.handler.framework");
        for (Class c: cl) {
            if (c.isAnnotationPresent(Path.class)) {
                for (Method method: c.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Path.class)) {
                        Path cp = (Path) c.getAnnotation(Path.class);
                        Path mp = (Path) method.getAnnotation(Path.class);
                        hMap.put((cp.value() + mp.value()), new Service((cp.value() + mp.value()), c, method));
                    }
                }
            }
        }
        return hMap;
    }


    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            PrintWriter pw = res.getWriter();
            String uri = req.getRequestURI();
            String[] ss = uri.split("/", 4);
            String compPath = "/" + ss[3];

            final int bufferSize = 1024;
            final char[] buffer = new char[bufferSize];
            final StringBuilder reqBody = new StringBuilder();
            int charsRead;

            InputStream inputStream = req.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            while ((charsRead = isr.read(buffer, 0, buffer.length)) > 0) {
                reqBody.append(buffer, 0, charsRead);
            }

            Service s = (Service) map.get(compPath);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            pw.println(s.invoke(reqBody.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class[] getClasses(String packageName)
    throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List < File > dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList();
        for (File directory: dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file: files) {
            if (file.isDirectory()) {
                assert!file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}