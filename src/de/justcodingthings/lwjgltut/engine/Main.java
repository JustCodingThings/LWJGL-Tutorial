package de.justcodingthings.lwjgltut.engine;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import de.justcodingthings.lwjgltut.game.Game;

public class Main {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private static Game game;
	
	private static GLFWErrorCallback errorCallback;
	private static long window;

	public static void main(String[] args) {
		// start initializing
		initEngine();
		initGL();
		initGame();
		// after initializing, start the main gameloop
		gameLoop();
	}

	private static void initEngine() {
		glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

		if (glfwInit() != GL_TRUE)
			throw new IllegalStateException("GLFW could not be initialized");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		
		window = glfwCreateWindow(WIDTH, HEIGHT, "LWJGL Tutorial", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Window could not be initialized");
		
		ByteBuffer videomode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(videomode) / 2), (GLFWvidmode.height(videomode) / 2));
		//set the window context
		glfwMakeContextCurrent(window);
		//set the swap intervall, 1 = VSYNC
		glfwSwapInterval(1);
		//finally, make the window visible
		glfwShowWindow(window);
		
		//create the GL context
		GLContext.createFromCurrent();
	}

	private static void initGL() {
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, -1, 1);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		//GL11.glLoadIdentity();
		
		GL11.glClearColor(1.0f, 0, 0, 1.0f);
	}

	private static void initGame() {
		game = new Game();
	}

	private static void gameLoop() {
		while (glfwWindowShouldClose(window) == GL_FALSE){
			glfwPollEvents();
			
			getInput();
			update();
			render();
		}
		cleanup();
		
	}
	
	private static void getInput() {
		game.getInput();
		
	}
	
	private static void update() {
		game.update();
	}
	
	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		///
		game.render();
		///
		glfwSwapBuffers(window);
	}
	
	private static void cleanup() {
		glfwDestroyWindow(window);
	}
	
	public static boolean isKeyDown(int key) {
		if (glfwGetKey(window, key) == GLFW_PRESS)
			return true;
		return false;
	}

}
