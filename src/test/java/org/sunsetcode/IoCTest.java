package org.sunsetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunsetcode.IoC.IoC;
import org.sunsetcode.IoC.exception.ResolveDependencyException;
import org.sunsetcode.IoC.exception.ScopeAlreadyExistException;
import org.sunsetcode.IoC.exception.ScopeDoesntExistsException;
import org.sunsetcode.command.Command;
import org.sunsetcode.gameobject.FuelTank;
import java.util.function.Function;

public class IoCTest
{
    @Test
    @DisplayName("Добавление и получение объекта из IoC")
    public void testPutAndGetObjectFromIoC() throws Exception {
        FuelTank fuelTankToSave = new FuelTank(100, 100, 10);
        IoC.<Command>resolve(
                "IoC.Registry",
                "GameObject.FuelTank",
                (Function<Object[], FuelTank>) (args) -> fuelTankToSave
        ).execute();

        var fuelTankFromIoC = IoC.<FuelTank>resolve("GameObject.FuelTank");
        Assertions.assertNotNull(fuelTankFromIoC);
        Assertions.assertEquals(fuelTankFromIoC.toString(), fuelTankToSave.toString());
    }

    @Test
    @DisplayName("Регистрация нового скоупа и взаимодействие с родительским скоупом")
    public void testNewScopeRegistrationAndChangeCurrentScopeBackToDefault() throws Exception
    {
        IoC.<Command>resolve(
                "IoC.Registry",
                "Player.Id",
                (Function<Object[], Integer>) (args) -> 1
        ).execute();
        Assertions.assertEquals(IoC.<Integer>resolve("Player.Id"), 1);

        IoC.<Command>resolve("Scopes.New", "test").execute();
        Assertions.assertEquals(IoC.<Integer>resolve("Player.Id"), 1);

        IoC.<Command>resolve(
                "IoC.Registry",
                "Player.Name",
                (Function<Object[], String>) (args) -> "test_player_name"
        ).execute();
        Assertions.assertEquals(IoC.<String>resolve("Player.Name"), "test_player_name");

        IoC.<Command>resolve("Scopes.Current", "default").execute();
        Assertions.assertThrows(ResolveDependencyException.class, () -> IoC.resolve("Player.Name"));
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создания скоупа с уже существующим именем")
    public void testScopeAlreadyExistsExceptionThrown()
    {
        Assertions.assertThrows(
                ScopeAlreadyExistException.class,
                () -> IoC.<Command>resolve("Scopes.New", "default").execute()
        );
    }

    @Test
    @DisplayName("Проверка ошибки при попытке переключиться на несуществующий скоуп")
    public void testScopeDoesntExistsExceptionThrown()
    {
        Assertions.assertThrows(
                ScopeDoesntExistsException.class,
                () -> IoC.<Command>resolve("Scopes.Current", "notExists").execute()
        );
    }
}
