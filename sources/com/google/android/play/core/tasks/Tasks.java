package com.google.android.play.core.tasks;

import android.support.annotation.NonNull;
import com.google.android.play.core.internal.ax;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class Tasks {
    private Tasks() {
    }

    public static <ResultT> Task<ResultT> a(ResultT resultt) {
        m mVar = new m();
        mVar.a(resultt);
        return mVar;
    }

    public static <ResultT> ResultT await(@NonNull Task<ResultT> task) throws ExecutionException, InterruptedException {
        ax.d(task, "Task must not be null");
        if (task.isComplete()) {
            return (ResultT) c(task);
        }
        n nVar = new n(null);
        d(task, nVar);
        nVar.a();
        return (ResultT) c(task);
    }

    public static <ResultT> ResultT await(@NonNull Task<ResultT> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        ax.d(task, "Task must not be null");
        ax.d(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return (ResultT) c(task);
        }
        n nVar = new n(null);
        d(task, nVar);
        if (nVar.b(j, timeUnit)) {
            return (ResultT) c(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <ResultT> Task<ResultT> b(Exception exc) {
        m mVar = new m();
        mVar.c(exc);
        return mVar;
    }

    private static <ResultT> ResultT c(Task<ResultT> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    private static void d(Task<?> task, n nVar) {
        task.addOnSuccessListener(TaskExecutors.a, nVar);
        task.addOnFailureListener(TaskExecutors.a, nVar);
    }
}
