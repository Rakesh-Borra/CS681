package umbcs681.hw4.color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Images {
	public static Image transform(Image image, Function<Color, Color> adjuster) {

        List<List<int[]>> matrix = new ArrayList<>();
        IntStream.range(0, image.getHeight()).forEach(x->{
            List<int[]> row=IntStream.range(0,image.getWidth()).mapToObj(y->new int[]{x,y}).collect(Collectors.toList());
            matrix.add(row);
        });


        Image adjusted = new Image(image.getHeight(), image.getWidth());
        matrix.stream().map(row->row.stream().map(pos->{
                   int x = pos[0];
                   int y = pos[1];
                   adjusted.setColor(x,y,adjuster.apply(image.getColor(x, y)));
                   return null;
               }).collect(Collectors.toList())
        ).collect(Collectors.toList()); 
        return adjusted;
    }

}
