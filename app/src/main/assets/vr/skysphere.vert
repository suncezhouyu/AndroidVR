uniform mat4 uProjMatrix;
uniform mat4 uViewMatrix;
uniform mat4 uModelMatrix;

attribute vec3 aPosition;
attribute vec2 aCoordinate;

varying vec2 vCoordinate;

void main(){
    gl_Position=uProjMatrix*uViewMatrix*uModelMatrix*vec4(aPosition,1);
    vCoordinate=aCoordinate;
}