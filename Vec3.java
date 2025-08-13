class Vec3 {
    double x, y, z;

    Vec3 (double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    Vec3 (double value){
        this.x = value;
        this.y = value;
        this.z = value;
    }
    Vec3 (double x, Vec2 other){
        this.x = x;
        this.y = other.x;
        this.z = other.y;
    }

    Vec3 sum (Vec3 other){
        return new Vec3(x+other.x, y+other.y, z+other.z);
    }

    Vec3 sub (Vec3 other){
        return new Vec3(x-other.x, y-other.y, z-other.z);
    }

    Vec3 mul (Vec3 other){
        return new Vec3(x*other.x, y*other.y, z*other.z);
    }

    Vec3 div (Vec3 other){
        return new Vec3(x/other.x, y/other.y, z/other.z);
    }

    Vec3 norm(){
        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        return new Vec3(x/length, y/length, z/length);
    }


    double scalar_product(Vec3 other) {
        return x * other.x + y * other.y + z * other.z;
    }
}
