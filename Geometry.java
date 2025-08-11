class Geometry {
    Vec2 sphIntersect (Vec3 rayOrigin, Vec3 rayDirection, double radius){
        double originProjectionOnRay = rayOrigin.scalar_product(rayDirection);
        double distanceSquaredFromCenter = rayOrigin.scalar_product(rayOrigin) - radius * radius;
        double discriminant = originProjectionOnRay * originProjectionOnRay - distanceSquaredFromCenter;
        if (discriminant < 0.0) return new Vec2(-1.0);

        double intersectionOffset = Math.sqrt(discriminant);
        return new Vec2(-originProjectionOnRay - intersectionOffset, -originProjectionOnRay + intersectionOffset);
    }
}
