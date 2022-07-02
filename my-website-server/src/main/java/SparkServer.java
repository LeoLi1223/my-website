import com.google.gson.Gson;
import parser.Blog;
import parser.BlogParser;
import projects.campuspaths.CampusMap;
import projects.campuspaths.datastructures.Path;
import projects.campuspaths.datastructures.Point;
import projects.campuspaths.parser.CampusBuilding;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import utils.CORSFilter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();

        // Respond to a "GET" request being made to the server's "/readBlogs" endpoint.
        // Sends a list of blogs (in markdown) in the Json format
        Spark.get("/readBlogs", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                List<Blog> blogList = BlogParser.parseBlogs("/blogs/");

                blogList.sort(new Comparator<Blog>() {
                    @Override
                    public int compare(Blog o1, Blog o2) {
                        try {
                            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                            Date date1 = dateFormat.parse(o1.getDate());
                            Date date2 = dateFormat.parse(o2.getDate());
                            if (date1.compareTo(date2) == 0) {
                                return o1.getTitle().compareTo(o2.getTitle());
                            }
                            // Sort blogs in the reverse order of create date.
                            return -1 * date1.compareTo(date2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            Spark.halt(400, "Cannot parse blog's creation date");
                            return -1;
                        }
                    }
                });

                Gson gson = new Gson();

                return gson.toJson(blogList);
            }
        });

        CampusMap map = new CampusMap();
        // Respond to a "GET" request being made to the server's "/findPath" endpoint.
        // Sends the shortest path between the given buildings in the Json format.
        Spark.get("/findPath", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String start = request.queryParams("start");
                String end = request.queryParams("end");

                if (!map.shortNameExists(start) || !map.shortNameExists(end)) {
                    Spark.halt(400, "Short names don't exist");
                }
                Path<Point> shortestPath = map.findShortestPath(start, end);

                Gson gson = new Gson();
                return gson.toJson(shortestPath);
            }
        });

        // Respond to a "GET" request being made to the server's "/getNames" endpoint.
        // Sends a list of buildings with its short and lone names in the Json format.
        Spark.get("/getNames", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                Map<String, String> shortToLongNames = map.buildingNames();

                List<CampusBuilding> bldgs = new ArrayList<>();
                for (Map.Entry<String, String> bldg: shortToLongNames.entrySet()) {
                    String shortName = bldg.getKey();
                    String longName = bldg.getValue();
                    bldgs.add(new CampusBuilding(shortName, longName, 0, 0));
                }
                bldgs.sort(new Comparator<CampusBuilding>() {
                    @Override
                    public int compare(CampusBuilding o1, CampusBuilding o2) {
                        return o1.getLongName().compareTo(o2.getLongName());
                    }
                });
                Gson gson = new Gson();
                return gson.toJson(bldgs);
            }
        });

    }
}
