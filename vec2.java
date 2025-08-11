class Vec2 {
    double x,y;

    Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    Vec2(double value) {
        this.x = value;
        this.y = value;
    }

    Vec2 sum (Vec2 other){
        return new Vec2(x+other.x, y+other.y);
    }

    Vec2 sub (Vec2 other){
        return new Vec2(x-other.x, y-other.y);
    }

    Vec2 mul (Vec2 other){
        return new Vec2(x*other.x, y*other.y);
    }

    Vec2 div (Vec2 other){
        return new Vec2(x/other.x, y/other.y);
    }



}
