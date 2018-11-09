package world;

public class Camera {
	
	float cameraX = 0f;
	float cameraY = 0f;
	

	
	public void update(float updatedX, float updatedY) {
		this.cameraX = updatedX;
		this.cameraY = updatedY;
	}

	public float getCameraX () {
		return cameraX;
	}

	public float getCameraY () {
		return cameraY;
	}

	
	
	
	
}
